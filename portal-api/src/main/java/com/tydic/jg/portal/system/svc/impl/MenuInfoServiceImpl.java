package com.tydic.jg.portal.system.svc.impl;

import com.tydic.jg.portal.system.dto.MenuInfo;
import com.tydic.jg.portal.system.dto.MenuRoutes;
import com.tydic.jg.portal.system.jpa.entity.MenuInfoEntity;
import com.tydic.jg.portal.system.jpa.entity.MenuRolesEntity;
import com.tydic.jg.portal.system.jpa.entity.SystemInfoEntity;
import com.tydic.jg.portal.system.jpa.repository.MenuInfoRepository;
import com.tydic.jg.portal.system.jpa.repository.MenuRolesRepository;
import com.tydic.jg.portal.system.jpa.repository.SystemInfoRepository;
import com.tydic.jg.portal.system.map.MenuInfoMapper;
import com.tydic.jg.portal.system.svc.MenuInfoService;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
public class MenuInfoServiceImpl implements MenuInfoService {

    private final MenuInfoRepository menuInfoRepository;
    private final MenuRolesRepository menuRolesRepository;
    private final SystemInfoRepository systemInfoRepository;

    @Autowired
    private MenuInfoMapper menuInfoMapper;

    @Override
    public MenuInfo create(MenuInfo menuInfo) {
        MenuInfoEntity entity = menuInfoRepository.save(menuInfoMapper.from(menuInfo));
        insertMenuRolers(menuInfo, entity);
        return menuInfoMapper.from(entity);
    }

    private void insertMenuRolers(MenuInfo menuInfo, MenuInfoEntity entity) {
        if(menuInfo.getRoles()!=null){
            List<MenuRolesEntity> list = new ArrayList<>();
            for (String role : menuInfo.getRoles()) {
                MenuRolesEntity rolesEntity = new MenuRolesEntity();
                rolesEntity.setMenuId(entity.getId());
                rolesEntity.setRole(role);
                rolesEntity.setDisabled(false);
                list.add(rolesEntity);
            }
            menuRolesRepository.saveAll(list);
        }
    }

    @Override
    public void delete(int id) {
        menuInfoRepository.deleteById(id);
    }

    @Override
    public void update(MenuInfo menuInfo) {
        MenuInfoEntity entity = menuInfoRepository.save(menuInfoMapper.from(menuInfo));
        menuRolesRepository.deleteMenuId(entity.getId());
        insertMenuRolers(menuInfo, entity);
    }

    @Override
    public MenuInfo getOne(int id) {

        MenuInfo menuInfo = menuInfoMapper.from(menuInfoRepository.getOne(id));
        List<String> list = new ArrayList<>();
        List<MenuRolesEntity> roles=menuRolesRepository.findMenuByRole(id);
        for (MenuRolesEntity role : roles) {
            list.add(role.getRole());
        }
        menuInfo.setRoles(list);
        return menuInfo;
    }

    @Override
    public List<MenuInfo> findAll(String search, Pageable pageable) {
        Page<MenuInfoEntity> userEntityList;
        if (StringUtils.hasText(search)) {
            userEntityList = menuInfoRepository.findAll(RSQLJPASupport.toSpecification(search), pageable);
        } else {
            userEntityList = menuInfoRepository.findAll(pageable);
        }
        return userEntityList.stream().map(entity -> menuInfoMapper.from(entity)).collect(Collectors.toList());
    }

    @Override
    public int deleteByIds(List<Integer> ids) {

        return menuInfoRepository.deleteByIds(ids);
    }

    /**
     * portal 自身页面路由列表 routes，
     * 返回当前用户所有角色的routes，合集，
     * 再将子系统中 base_router 拼接在菜单path之前，判断首位 "/"
     *
     */
    @Override
    public List<MenuRoutes> routes(Collection<? extends GrantedAuthority> authorities) {
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        List<SystemInfoEntity> entities = systemInfoRepository.findByType(0);

        List<MenuRoutes> returnMenu = new ArrayList<>();

        Map<Integer,String> keyMap = new HashMap<>();
        for(SystemInfoEntity entity : entities){
//            String base = entity.getRouterBase().startsWith("/") ? entity.getRouterBase() : "/" + entity.getRouterBase();
            String base = entity.getRouterBase().endsWith("/") ? entity.getRouterBase().substring(0,entity.getRouterBase().length()-1) : entity.getRouterBase();
            keyMap.put(entity.getId(),base);
        }

        List<MenuRoutes> list = menuInfoMapper.routesfromEntity(menuInfoRepository.findByRoles(roles));
        for(MenuRoutes route : list){
            if(keyMap.get(route.getSysId()) != null){
                String path = route.getPath().startsWith("/") ? keyMap.get(route.getSysId()) + route.getPath() : keyMap.get(route.getSysId()) + "/" + route.getPath();
                route.setPath(path);
                returnMenu.add(route);
            }
        }
        return returnMenu;
    }

}
