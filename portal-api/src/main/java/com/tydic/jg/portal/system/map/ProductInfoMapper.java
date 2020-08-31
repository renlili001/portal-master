package com.tydic.jg.portal.system.map;

import com.tydic.jg.portal.system.dto.ProductInfo;
import com.tydic.jg.portal.system.dto.ProductMenus;
import com.tydic.jg.portal.system.jpa.entity.ProductInfoEntity;
import com.tydic.jg.portal.system.jpa.entity.ProductMenusEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductInfoMapper {

    @Mappings({})
    ProductInfo from(ProductInfoEntity entity);

    @Mappings({})
    ProductInfoEntity from(ProductInfo productInfo);

    @Mappings({})
    List<ProductInfo> fromBuild(List<ProductInfoEntity> productRole);

    @Mappings({})
    List<ProductMenus> from(List<ProductMenusEntity> list);
}
