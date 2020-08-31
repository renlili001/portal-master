package com.tydic.jg.portal.system.svc.impl;

import com.tydic.jg.portal.system.dto.ProductInfo;
import com.tydic.jg.portal.system.dto.ProductMenus;
import com.tydic.jg.portal.system.jpa.entity.ProductInfoEntity;
import com.tydic.jg.portal.system.jpa.repository.ProductInfoRepository;
import com.tydic.jg.portal.system.map.ProductInfoMapper;
import com.tydic.jg.portal.system.svc.ProductInfoService;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
public class ProductInfoServiceImpl implements ProductInfoService {
    private final ProductInfoRepository productInfoRepository;

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private ClientResource productClient;

    private static final Integer INT0 = 0;

    @Autowired
    private UsersResource usersResource;
    @Autowired
    private ClientResource productResource;

    @Autowired
    private RolesResource rolesResource;

    @Override
    public ProductInfo create(ProductInfo productInfo) {
        ProductInfoEntity save = productInfoRepository.save(productInfoMapper.from(productInfo));
        save.setRole(getRoleName(save.getId()));

        final RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setName(save.getRole());
        productClient.roles().create(roleRepresentation);

        return productInfoMapper.from(save);
    }

    private String getRoleName(int id) {
        return "product.role"+"_"+ id;
    }

    @Override
    public void delete(int id) {
        productInfoRepository.deleteById(id);
        try{
            productClient.roles().deleteRole(getRoleName(id));
        }catch (NotFoundException ignore){

        }
    }

    @Override
    public void update(ProductInfo productInfo) {
        productInfoRepository.save(productInfoMapper.from(productInfo));
    }

    @Override
    public ProductInfo getOne(int id) {
        return productInfoMapper.from(productInfoRepository.getOne(id));
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return productInfoRepository.deleteByIds(ids);
    }

    @Override
    public List<ProductInfo> findAll(String search, Pageable pageable) {
        Page<ProductInfoEntity> userEntityList;
        if (StringUtils.hasText(search)) {
            userEntityList = productInfoRepository.findAll(RSQLJPASupport.toSpecification(search), pageable);
        } else {
            userEntityList = productInfoRepository.findAll(pageable);
        }

        return userEntityList.stream().map(entity -> productInfoMapper.from(entity)).collect(Collectors.toList());
    }

    @Override
    public List<ProductInfo> getByRoles(Collection<? extends GrantedAuthority> authorities, boolean showMenus) {
        List<String> list = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        List<ProductInfo> infoList = productInfoMapper.fromBuild(productInfoRepository.getByRole(list));

        if(!showMenus){
            return infoList;
        }else{
            for(ProductInfo info : infoList){
                info.setMenus(getProductMenu(info.getId(), authorities));
            }
        }
        return infoList;
    }

    @Override
    public List<ProductMenus> getProductMenu(Integer infoId, Collection<? extends GrantedAuthority> authorities) {
        List<String> list = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        List<ProductMenus> entities = productInfoMapper.from(productInfoRepository.getProductMenu(infoId,list));
        return build(0,entities);
    }

    @Override
    public void subscribe(int id) {
        final UserResource currentUser = getCurrentUser();
        final ProductInfo productInfo =getOne(id);
        String role = productInfo.getRole();
        if(role.startsWith("product.")){
            role = role.substring("product.".length());
            final RoleResource roleResource = productResource.roles().get(role);
            RoleRepresentation roleRepresentation = null;
            try {
                roleRepresentation = roleResource.toRepresentation();
            }catch (NotFoundException ex){
                roleRepresentation = new RoleRepresentation();
                roleRepresentation.setName(role);
                productResource.roles().create(roleRepresentation);
                roleRepresentation = roleResource.toRepresentation();
            }
            currentUser.roles()
                    .clientLevel(roleRepresentation.getContainerId())
                    .add(Collections.singletonList(roleRepresentation));
        }else{
            final RoleRepresentation roleResource = rolesResource.get(role).toRepresentation();
            currentUser.roles().realmLevel().add(Collections.singletonList(roleResource));
        }
    }

    @Override
    public void unSubscribe(int id) {
        final UserResource currentUser = getCurrentUser();
        final ProductInfo productInfo = getOne(id);
        String role = productInfo.getRole();
        if(role.startsWith("product.")) {
            role = role.substring("product.".length());
            final RoleResource roleResource = productResource.roles().get(role);
            final RoleRepresentation roleRepresentation = roleResource.toRepresentation();
            currentUser.roles()
                    .clientLevel(roleRepresentation.getContainerId())
                    .remove(Collections.singletonList(roleRepresentation));
        }else{
            final RoleRepresentation roleResource = rolesResource.get(role).toRepresentation();
            currentUser.roles()
                    .realmLevel()
                    .remove(Collections.singletonList(roleResource));
        }
    }

    private UserResource getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        final KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        final String userId = principal.getKeycloakSecurityContext().getToken().getSubject();
        return usersResource.get(userId);
    }

    private static List<ProductMenus> build(int pid,List<ProductMenus> list){
        List<ProductMenus> buildList = new ArrayList<>();
        //如果  id != getParentId && getParentId == pid 则继续调用
        list.stream().filter(menu -> (menu.getId() != menu.getParentId() && pid == menu.getParentId())).forEach(menu -> {
            menu.setChildren(build(menu.getId(), list));
            buildList.add(menu);
        });
        //如果  id == getParentId ，直接添加到list中
        list.stream().filter(menu -> (menu.getId() == menu.getParentId())).forEach(menu -> buildList.add(menu));
        getSort(buildList);
        return buildList;
    }

    private static void getSort(List<ProductMenus> buildList) {
        try {
            buildList.sort((o1, o2) -> {
                // 如果排序ID不存在，返回0
                if(o1.getMenuOrder() == INT0|| o2.getMenuOrder() == INT0) {
                    return INT0;
                }
                return o1.getMenuOrder() - o2.getMenuOrder();
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
