package com.tydic.jg.portal.system.svc.impl;

import com.tydic.jg.portal.system.dto.SystemInfo;
import com.tydic.jg.portal.system.jpa.entity.SystemInfoEntity;
import com.tydic.jg.portal.system.jpa.repository.SystemInfoRepository;
import com.tydic.jg.portal.system.map.SystemInfoMapper;
import com.tydic.jg.portal.system.svc.SystemInfoService;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
public class SystemInfoServiceImpl implements SystemInfoService {
    private final SystemInfoRepository systemInfoRepository;

    @Autowired
    private SystemInfoMapper systemInfoMapper;
    @Override
    public SystemInfo create(SystemInfo systemInfo) {
        SystemInfoEntity save = systemInfoRepository.save(systemInfoMapper.from(systemInfo));
        return systemInfoMapper.from(save);
    }

    @Override
    public void delete(Integer id) {
        systemInfoRepository.deleteById(id);
    }

    @Override
    public void update(SystemInfo systemInfo) {
        systemInfoRepository.save(systemInfoMapper.from(systemInfo));
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return systemInfoRepository.deleteByIds(ids);
    }

    @Override
    public SystemInfo getOne(int id) {
        return systemInfoMapper.from(systemInfoRepository.getOne(id));
    }

    @Override
    public List<SystemInfo> findAll(String search, Pageable pageable) {
        Page<SystemInfoEntity> userEntityList;
        if (StringUtils.hasText(search)) {
            userEntityList = systemInfoRepository.findAll(RSQLJPASupport.toSpecification(search), pageable);
        } else {
            userEntityList = systemInfoRepository.findAll(pageable);
        }
        return userEntityList.stream().map(entity -> systemInfoMapper.from(entity)).collect(Collectors.toList());
    }
}
