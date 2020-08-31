package com.tydic.jg.portal.menu.map;

import com.tydic.jg.portal.menu.dto.Menu;
import com.tydic.jg.portal.menu.dto.MenuBuild;
import com.tydic.jg.portal.menu.jpa.entity.SysMenuEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    @Mappings({})
    Menu from(SysMenuEntity entity);

    @Mappings({})
    SysMenuEntity from(Menu menu);

    @Mappings({})
    List<Menu> from(List<SysMenuEntity> list);

    @Mappings({
            @Mapping(source = "title", target = "meta.title"),
            @Mapping(source = "icon", target = "meta.icon"),
            @Mapping(source = "cache", target = "meta.noCache"),
            @Mapping(source = "iFrame", target = "meta.IFrame"),
            @Mapping(source = "mount", target = "meta.mount"),
            @Mapping(source = "subsysShow", target = "meta.subsysShow")
    })
    MenuBuild fromBuild(SysMenuEntity entity);

    @Mappings({})
    List<MenuBuild> fromBuild(List<SysMenuEntity> menuByRole);
}
