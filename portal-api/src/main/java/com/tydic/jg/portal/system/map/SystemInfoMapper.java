package com.tydic.jg.portal.system.map;

import com.tydic.jg.portal.system.dto.SystemInfo;
import com.tydic.jg.portal.system.jpa.entity.SystemInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SystemInfoMapper {
    @Mappings({})
    SystemInfo from(SystemInfoEntity entity);

    @Mappings({})
    SystemInfoEntity from(SystemInfo systemInfo);
}
