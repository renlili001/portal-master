package com.tydic.jg.portal.menu.svc;

import com.tydic.jg.portal.menu.dto.Menu;
import com.tydic.jg.portal.menu.dto.MenuBuild;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

public interface MenuService {

    default List<Menu> findAll(String search){
        return findAll(search, Pageable.unpaged());
    }

    List<Menu> findAll(String search, Pageable pageable);

    Menu getOne(int id);

    void create(Menu menu);

    void delete(int id);

    void update(Menu menu);

    int deleteByIds(@RequestParam(value = "ids")List<Integer> ids);

    List<Menu> menuTree();

    List<MenuBuild> buildMen(Collection<? extends GrantedAuthority> authorities);
}
