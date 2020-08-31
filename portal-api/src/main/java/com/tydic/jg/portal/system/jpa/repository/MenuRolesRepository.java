package com.tydic.jg.portal.system.jpa.repository;

import com.tydic.jg.portal.system.jpa.entity.MenuRolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MenuRolesRepository extends JpaRepository<MenuRolesEntity,Integer>, JpaSpecificationExecutor<MenuRolesEntity> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="delete from menu_roles where menu_id in (?1) ")
    void deleteMenuId(Integer id);


    @Query(nativeQuery = true,value = "select * from menu_roles where role != 'portal_all_user' and menu_id=?1")
    List<MenuRolesEntity> findMenuByRole(int menuId);
}
