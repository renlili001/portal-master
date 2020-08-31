package com.tydic.jg.portal.system.svc;

import com.tydic.jg.portal.system.dto.ProductInfo;
import com.tydic.jg.portal.system.dto.ProductMenus;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface ProductInfoService {
    ProductInfo create(ProductInfo productInfo);

    void delete(int id);

    void update(ProductInfo productInfo);

    ProductInfo getOne(int id);

    int deleteByIds(List<Integer> ids);

    default List<ProductInfo> findAll(String search){
        return findAll(search, Pageable.unpaged());
    }

    List<ProductInfo> findAll(String search, Pageable pageable);

    List<ProductInfo> getByRoles(Collection<? extends GrantedAuthority> authorities, boolean showMenus);

    List<ProductMenus> getProductMenu(Integer infoId, Collection<? extends GrantedAuthority> authorities);

    void subscribe(int id);

    void unSubscribe(int id);
}
