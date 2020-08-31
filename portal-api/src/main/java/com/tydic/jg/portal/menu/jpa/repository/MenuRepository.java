package com.tydic.jg.portal.menu.jpa.repository;

import com.tydic.jg.portal.menu.jpa.entity.SysMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MenuRepository extends JpaRepository<SysMenuEntity,Integer>, JpaSpecificationExecutor<SysMenuEntity> {

    @Query(nativeQuery = true, value="select a.* from sys_menu a, sys_roles_menus b,sys_subsys c where a.subsys_id = c.subsys_id AND b.menu_id != 0 AND a.menu_id=b.menu_id and c.type=0 and b.role in(?1)")
    List<SysMenuEntity> getMenuByRole(List<String> role);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="delete from sys_menu where menu_id in (?1) ")
    int deleteByIds(List<Integer> ids);

    @Query(nativeQuery = true,value = "select * from sys_menu where subsys_id=?1 ")
    List<SysMenuEntity> queryBySysId(int id);

}
