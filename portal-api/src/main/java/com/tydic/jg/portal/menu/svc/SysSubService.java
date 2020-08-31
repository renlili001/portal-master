package com.tydic.jg.portal.menu.svc;

import com.tydic.jg.portal.menu.dto.SysSub;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysSubService {

    void create(SysSub sysSub);

    void delete(int id);

    SysSub getOne(int id);

    void update(SysSub sysSub);

    default List<SysSub> findAll(String search){
        return findAll(search, Pageable.unpaged());
    }

    List<SysSub> findAll(String search, Pageable pageable);

    List<SysSub> queryMenu();
}
