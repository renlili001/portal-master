package com.tydic.jg.portal.menu.map;

import com.tydic.jg.portal.menu.dto.SysSub;
import com.tydic.jg.portal.menu.jpa.entity.SysSubsysEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SysSubMapper {
    @Mappings({})
    SysSub from(SysSubsysEntity entity);

    @Mappings({})
    SysSubsysEntity from(SysSub SysSub);

    @Mappings({})
    List<SysSub> from(List<SysSubsysEntity> list);


}