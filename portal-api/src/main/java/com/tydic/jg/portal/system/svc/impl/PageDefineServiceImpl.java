package com.tydic.jg.portal.system.svc.impl;

import com.tydic.jg.portal.system.dto.PageDefine;
import com.tydic.jg.portal.system.jpa.entity.PageDefineEntity;
import com.tydic.jg.portal.system.jpa.repository.PageDefineRepository;
import com.tydic.jg.portal.system.map.PageDefineMapper;
import com.tydic.jg.portal.system.svc.PageDefineService;
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
public class PageDefineServiceImpl implements PageDefineService {

    private final PageDefineRepository pageDefineRepository;
    @Autowired
    private PageDefineMapper pageDefineMapper;

    @Override
    public PageDefine create(PageDefine pageDefine) {

        PageDefineEntity save = pageDefineRepository.save(pageDefineMapper.from(pageDefine));
        return pageDefineMapper.from(save);
    }

    @Override
    public void delete(int id) {
        pageDefineRepository.deleteById(id);
    }

    @Override
    public void update(PageDefine pageDefine) {
        pageDefineRepository.save(pageDefineMapper.from(pageDefine));
    }

    @Override
    public PageDefine getOne(int id) {
        return pageDefineMapper.from(pageDefineRepository.getOne(id));
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return pageDefineRepository.deleteByIds(ids);
    }

    @Override
    public List<PageDefine> findAll(String search, Pageable pageable) {
        Page<PageDefineEntity> userEntityList;
        if (StringUtils.hasText(search)) {
            userEntityList = pageDefineRepository.findAll(RSQLJPASupport.toSpecification(search), pageable);
        } else {
            userEntityList = pageDefineRepository.findAll(pageable);
        }
        return userEntityList.stream().map(entity -> pageDefineMapper.from(entity)).collect(Collectors.toList());
   }

}
