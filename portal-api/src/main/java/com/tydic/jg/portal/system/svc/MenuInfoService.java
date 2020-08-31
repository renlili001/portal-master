package com.tydic.jg.portal.system.svc;

import com.tydic.jg.portal.system.dto.MenuInfo;
import com.tydic.jg.portal.system.dto.MenuRoutes;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface MenuInfoService {
    MenuInfo create(MenuInfo menuInfo);

    void delete(int id);

    void update(MenuInfo menuInfo);

    MenuInfo getOne(int id);

    default List<MenuInfo> findAll(String search){
        return findAll(search, Pageable.unpaged());
    }

    List<MenuInfo> findAll(String search, Pageable pageable);

    int deleteByIds( List<Integer> ids);

    List<MenuRoutes> routes(Collection<? extends GrantedAuthority> authorities);
}
