package com.tydic.jg.portal.menu.jpa.repository;

import com.tydic.jg.portal.menu.jpa.entity.SysSubsysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysSubRepository extends JpaRepository<SysSubsysEntity,Integer>, JpaSpecificationExecutor<SysSubsysEntity> {

}
