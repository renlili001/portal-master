package com.tydic.jg.portal.system.jpa.repository;

import com.tydic.jg.portal.system.jpa.entity.PageDefineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface PageDefineRepository extends JpaRepository<PageDefineEntity,Integer>, JpaSpecificationExecutor<PageDefineEntity> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="delete from page_define where id in (?1) ")
    int deleteByIds(List<Integer> ids);


}
