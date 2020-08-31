package com.tydic.jg.portal.system.svc;

import com.tydic.jg.portal.system.dto.SystemInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SystemInfoService {
    SystemInfo create(SystemInfo systemInfo);

    void delete(Integer id);

    void update(SystemInfo systemInfo);

    int deleteByIds(List<Integer> ids);

    SystemInfo getOne(int id);

    default List<SystemInfo> findAll(String search){
        return findAll(search, Pageable.unpaged());
    }

    List<SystemInfo> findAll(String search, Pageable pageable);
}
