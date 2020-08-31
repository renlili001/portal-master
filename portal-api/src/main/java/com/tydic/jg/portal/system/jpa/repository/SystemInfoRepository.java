package com.tydic.jg.portal.system.jpa.repository;

import com.tydic.jg.portal.system.jpa.entity.SystemInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SystemInfoRepository extends JpaRepository<SystemInfoEntity,Integer>, JpaSpecificationExecutor<SystemInfoEntity> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="delete from system_info where id in (?1) ")
    int deleteByIds(List<Integer> ids);

    @Transactional
    @Query(nativeQuery = true,value="select * from system_info where type=?1 ")
    List<SystemInfoEntity> findByType(int i);
}
