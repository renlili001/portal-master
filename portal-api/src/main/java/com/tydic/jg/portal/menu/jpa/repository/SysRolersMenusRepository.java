package com.tydic.jg.portal.menu.jpa.repository;

import com.tydic.jg.portal.menu.jpa.entity.SysRolesMenusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysRolersMenusRepository extends JpaRepository<SysRolesMenusEntity,Integer>, JpaSpecificationExecutor<SysRolesMenusEntity> {
}
