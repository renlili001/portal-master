package com.tydic.jg.portal.system.svc;

import com.tydic.jg.portal.system.dto.ProductDefine;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDefineService {

    ProductDefine create(ProductDefine productDefine);

    void delete(int id);

    void update(ProductDefine productDefine);

    ProductDefine getOne(int id);

    int deleteByIds(List<Integer> ids);

    default List<ProductDefine> findAll(String search){
        return findAll(search, Pageable.unpaged());
    }

    List<ProductDefine> findAll(String search, Pageable pageable);

    List<ProductDefine> findByProduct(int infoId);
}
