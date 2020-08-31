package com.tydic.jg.portal.menu.svc.impl;

import com.tydic.jg.portal.menu.dto.Menu;
import com.tydic.jg.portal.menu.dto.SysSub;
import com.tydic.jg.portal.menu.jpa.entity.SysSubsysEntity;
import com.tydic.jg.portal.menu.jpa.repository.MenuRepository;
import com.tydic.jg.portal.menu.jpa.repository.SysSubRepository;
import com.tydic.jg.portal.menu.map.MenuMapper;
import com.tydic.jg.portal.menu.map.SysSubMapper;
import com.tydic.jg.portal.menu.svc.SysSubService;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SysSubServiceImpl implements SysSubService {
    private final SysSubRepository sysSubRepository;

    private final MenuRepository menuRepository;

    @Autowired
    private SysSubMapper sysSubMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public void create(SysSub sysSub) {
        sysSubRepository.save(sysSubMapper.from(sysSub));
    }

    @Override
    public void delete(int id) {
        sysSubRepository.deleteById(id);
    }

    @Override
    public SysSub getOne(int id) {
        return sysSubMapper.from(sysSubRepository.getOne(id));
    }

    @Override
    public void update(SysSub sysSub) {
        sysSubRepository.save(sysSubMapper.from(sysSub));
    }

    @Override
    public List<SysSub> findAll(String search, Pageable pageable) {
        Page<SysSubsysEntity> sysEntityList;
        if(StringUtils.hasText(search)){
            sysEntityList = sysSubRepository.findAll(RSQLJPASupport.toSpecification(search),pageable);
        }else{
            sysEntityList = sysSubRepository.findAll(pageable);
        }
        return sysEntityList.stream().map(entity -> sysSubMapper.from(entity)).collect(Collectors.toList());
    }
    @Override
    public List<SysSub> queryMenu() {
        List<SysSub> list = sysSubMapper.from(sysSubRepository.findAll());
        for (SysSub sysSub : list) {
            List<Menu> menus= menuMapper.from(menuRepository.queryBySysId(sysSub.getSubsysId()));
            sysSub.setMenus(menus);
        }
        return list;
    }


}
