package com.tydic.jg.portal.system.map;

import com.tydic.jg.portal.system.dto.ProductDefine;
import com.tydic.jg.portal.system.jpa.entity.ProductDefineEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductDefineMapper {

    @Mappings({})
    ProductDefine from(ProductDefineEntity entity);

    @Mappings({})
    ProductDefineEntity from(ProductDefine productDefine);

    @Mappings({})
    List<ProductDefine> from(List<ProductDefineEntity> entities);
}
