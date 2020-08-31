package com.tydic.jg.portal.system.jpa.repository;

import com.tydic.jg.portal.system.jpa.entity.MenuInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MenuInfoRepository extends JpaRepository<MenuInfoEntity,Integer>, JpaSpecificationExecutor<MenuInfoEntity> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="delete from menu_info where id in (?1) ")
    int deleteByIds(List<Integer> ids);

    @Transactional
    @Query(nativeQuery = true,value="select b.* from system_info a,menu_info b, menu_roles c where b.sys_id = a.id and c.menu_id = b.id and a.type = 0 and (c.role = 'portal_all_user' OR (c.role in (?1)))")
    List<MenuInfoEntity> findByRoles(List<String> roles);
}
