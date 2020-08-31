package com.tydic.jg.portal.system.map;

import com.tydic.jg.portal.system.dto.MenuInfo;
import com.tydic.jg.portal.system.dto.MenuRoutes;
import com.tydic.jg.portal.system.jpa.entity.MenuInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuInfoMapper {
    @Mappings({})
    MenuInfo from(MenuInfoEntity entity);

    @Mappings({})
    MenuInfoEntity from(MenuInfo menuInfo);

    @Mappings({})
    List<MenuInfo> from(List<MenuInfoEntity> list);

    @Mappings({
            @Mapping(source = "title", target = "meta.title"),
            @Mapping(source = "icon", target = "meta.icon")
    })
    MenuRoutes routesfromEntity(MenuInfoEntity entity);

    List<MenuRoutes> routesfromEntity(List<MenuInfoEntity> list);
}
