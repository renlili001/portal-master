package com.tydic.jg.portal.system.map;

import com.tydic.jg.portal.system.dto.PageDefine;
import com.tydic.jg.portal.system.jpa.entity.PageDefineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PageDefineMapper {
    @Mappings({})
    PageDefine from(PageDefineEntity entity);

    @Mappings({})
    PageDefineEntity from(PageDefine PageDefine);
}
