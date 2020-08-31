package com.tydic.jg.portal.system.jpa.repository;

import com.tydic.jg.portal.system.jpa.entity.ProductInfoEntity;
import com.tydic.jg.portal.system.jpa.entity.ProductMenusEntity;
import com.tydic.jg.portal.utils.jpa.NativeQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductInfoRepository extends NativeQueryRepository, JpaRepository<ProductInfoEntity,Integer>, JpaSpecificationExecutor<ProductInfoEntity> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value="delete from product_info where id in (?1) ")
    int deleteByIds(List<Integer> ids);


    @Query(nativeQuery = true, value="select * from product_info  where role='portal_all_user' OR role in(?1) order by id ASC")
    List<ProductInfoEntity> getByRole(List<String> role);

    default List<ProductMenusEntity> getProductMenu(Integer infoId, List<String> list){
        String sql = "select \n" +
                "distinct\n" +
                "b.id,\n" +
                "a.name product_name,\n" +
                "b.parent_id parent_id,\n" +
                "b.product_id product_id,\n" +
                "b.menu_id menu_id,\n" +
                "b.menu_order, \n" +
                "c.type,\n" +
                "ifnull(b.icon,c.icon) icon,\n" +
                "ifnull(b.menu_name,c.title) title,\n" +
                "ifnull(b.cache,c.cache) cache,\n" +
                "ifnull(b.hidden,c.hidden) hidden,\n" +
                "c.name,\n" +
                "c.sys_id sys_id,\n" +
                "c.icon,\n" +
                "c.component,\n" +
                "c.path,\n" +
                "c.external,\n" +
                "e.router_base router_base\n" +
                "from product_info a,product_define b,menu_info c,menu_roles d,system_info e\n" +
                "where\n" +
                "a.id=b.product_id \n" +
                "and b.menu_id=c.id \n" +
                "and c.sys_id=e.id  \n" +
                "and ((c.id=d.menu_id and d.role in (?2)) OR (d.role='portal_all_user'))\n" +
                "and ((a.role in (?2)) OR a.role='portal_all_user')\n" +
                "and a.id=?1";
        return nativeQuery(sql, ProductMenusEntity.class, infoId, list);
    }


}
