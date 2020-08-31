package com.tydic.jg.portal.system.jpa.repository;

import com.tydic.jg.portal.system.jpa.entity.ProductDefineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDefineRepository extends JpaRepository<ProductDefineEntity,Integer>, JpaSpecificationExecutor<ProductDefineEntity> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="delete from product_define where id in (?1) ")
    int deleteByIds(List<Integer> ids);

    @Query(nativeQuery = true,value="select *  from product_define where product_id = (?1) order by id ASC")
    List<ProductDefineEntity> findByProduct(int infoId);
}
