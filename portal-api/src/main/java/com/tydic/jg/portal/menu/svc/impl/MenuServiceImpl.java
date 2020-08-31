package com.tydic.jg.portal.menu.svc.impl;

import com.tydic.jg.portal.menu.dto.Menu;

import com.tydic.jg.portal.menu.dto.MenuBuild;

import com.tydic.jg.portal.menu.jpa.entity.SysMenuEntity;

import com.tydic.jg.portal.menu.jpa.entity.SysRolesMenusEntity;

import com.tydic.jg.portal.menu.jpa.repository.MenuRepository;

import com.tydic.jg.portal.menu.jpa.repository.SysRolersMenusRepository;

import com.tydic.jg.portal.menu.map.MenuMapper;

import com.tydic.jg.portal.menu.svc.MenuService;

import io.github.perplexhub.rsql.RSQLJPASupport;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;

import org.springframework.data.domain.Pageable;

import org.springframework.security.core.GrantedAuthority;

import org.springframework.util.StringUtils;

import java.util.ArrayList;

import java.util.Collection;

import java.util.List;

import java.util.stream.Collectors;

//@Transactional

@RequiredArgsConstructor

public class MenuServiceImpl implements MenuService {

    private static final Integer INT = 0;

    private final MenuRepository menuRepository;

    private final SysRolersMenusRepository sysRolersMenusRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findAll(String search,  Pageable pageable) {
        Page<SysMenuEntity> userEntityList;
        if (StringUtils.hasText(search)) {
            userEntityList = menuRepository.findAll(RSQLJPASupport.toSpecification(search), pageable);
        } else {
            userEntityList = menuRepository.findAll(pageable);
        }
        return userEntityList.stream().map(entity -> menuMapper.from(entity)).collect(Collectors.toList());
    }

    @Override
    public Menu getOne(int id) {
        return menuMapper.from(menuRepository.getOne(id));
    }

    @Override
    public void create(Menu menu) {
        SysMenuEntity entity = menuRepository.save(menuMapper.from(menu));
        List<SysRolesMenusEntity> list = new ArrayList<>();
        for (String role : menu.getRoles()) {
            SysRolesMenusEntity entity1 = new SysRolesMenusEntity();
            entity1.setMenuId(entity.getMenuId());
            entity1.setRole(role);
            entity1.setSubsysId(0);
            entity1.setDisabled(false);
            list.add(entity1);
        }
        sysRolersMenusRepository.saveAll(list);

    }

    @Override
    public void delete(int id) {
        menuRepository.deleteById(id);
    }

    @Override
    public void update(Menu menu) {
        menuRepository.save(menuMapper.from(menu));
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return menuRepository.deleteByIds(ids);
    }

    @Override
    public List<Menu> menuTree() {
        List<Menu> menuList = menuMapper.from(menuRepository.findAll());
        return build(0, menuList);
    }

    // 菜单在角色表中有对应的权限，并且 子系统的类型为0(是门户本身页面)的情况才会返回结果；
    // 这个接口用于  前端Vue生成router
    @Override
    public List<MenuBuild> buildMen(Collection<? extends GrantedAuthority> authorities) {

        List<String> list = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        List<MenuBuild> menuList = menuMapper.fromBuild(menuRepository.getMenuByRole(list));
        return buildMenuTree(0, menuList).get(0).getChildren();
    }

    private static List<Menu> build(int pid,List<Menu> list){
        List<Menu> buildList = new ArrayList<>();
        list.stream().filter(menu -> pid == menu.getPid()).forEach(menu -> {
            menu.setChildren(build(menu.getMenuId(), list));
            buildList.add(menu);
        });
        return buildList;
    }

    private static List<MenuBuild> buildMenuTree(int pid,List<MenuBuild> menuList) {
        List<MenuBuild> buildList = new ArrayList<>();
        menuList.stream().filter(menuBuild -> pid == menuBuild.getPid()).forEach(menuBuild -> {
            menuBuild.getMeta().setNoCache(!menuBuild.getMeta().getNoCache());
            if (menuBuild.getType() == INT || menuBuild.getType() == 1) {
                menuBuild.setRedirect("noredirect");
                menuBuild.setAlwaysShow(true);
                menuBuild.setPath("/" + menuBuild.getPath());
            }
            buildList.add(menuBuild);
            if (menuBuild.getSubCount() != null && menuBuild.getSubCount() > INT) {
                menuBuild.setChildren(buildMenuTree(menuBuild.getMenuId(), menuList));
            }
        });
        getSort(buildList);
        return buildList;
    }

    private static void getSort(List<MenuBuild> buildList) {
        buildList.sort((o1, o2) -> {
            // 如果排序ID不存在，返回0
            if(o1.getMenuSort() == INT|| o2.getMenuSort() == INT) {
                return INT;
            }
            return o1.getMenuSort() - o2.getMenuSort();
        });
    }

}
