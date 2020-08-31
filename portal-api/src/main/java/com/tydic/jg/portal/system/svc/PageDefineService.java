package com.tydic.jg.portal.system.svc;


import com.tydic.jg.portal.system.dto.PageDefine;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PageDefineService {

    PageDefine create(PageDefine pageDefine);

    void delete(int id);

    void update(PageDefine pageDefine);

    PageDefine getOne(int id);

    int deleteByIds(List<Integer> ids);

    default List<PageDefine> findAll(String search){
        return findAll(search, Pageable.unpaged());
    }

    List<PageDefine> findAll(String search, Pageable pageable);
}
