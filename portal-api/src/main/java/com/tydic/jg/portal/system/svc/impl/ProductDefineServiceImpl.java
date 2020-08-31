package com.tydic.jg.portal.system.svc.impl;

import com.tydic.jg.portal.system.dto.ProductDefine;
import com.tydic.jg.portal.system.jpa.entity.ProductDefineEntity;
import com.tydic.jg.portal.system.jpa.repository.ProductDefineRepository;
import com.tydic.jg.portal.system.map.ProductDefineMapper;
import com.tydic.jg.portal.system.svc.ProductDefineService;
import io.github.perplexhub.rsql.RSQLJPASupport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
public class ProductDefineServiceImpl implements ProductDefineService {
    private final ProductDefineRepository productDefineRepository;

    @Autowired
    private ProductDefineMapper productDefineMapper;

    private static final Integer INT0 = 0;
    @Override
    public ProductDefine create(ProductDefine productDefine) {

        ProductDefineEntity save = productDefineRepository.save(productDefineMapper.from(productDefine));
        return productDefineMapper.from(save);
    }

    @Override
    public void delete(int id) {
        productDefineRepository.deleteById(id);
    }

    @Override
    public void update(ProductDefine productDefine) {
        productDefineRepository.save(productDefineMapper.from(productDefine));
    }

    @Override
    public ProductDefine getOne(int id) {
        return productDefineMapper.from(productDefineRepository.getOne(id));
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
        return productDefineRepository.deleteByIds(ids);
    }

    @Override
    public List<ProductDefine> findAll(String search, Pageable pageable) {
        Page<ProductDefineEntity> userEntityList;
        if (StringUtils.hasText(search)) {
            userEntityList = productDefineRepository.findAll(RSQLJPASupport.toSpecification(search), pageable);
        } else {
            userEntityList = productDefineRepository.findAll(pageable);
        }
        return userEntityList.stream().map(entity -> productDefineMapper.from(entity)).collect(Collectors.toList());
    }

    @Override
    public List<ProductDefine> findByProduct(int infoId) {

        List<ProductDefine> list = productDefineMapper.from(productDefineRepository.findByProduct(infoId));

        return build(0,list);
    }
    private static List<ProductDefine> build(int pid,List<ProductDefine> list){
        List<ProductDefine> buildList = new ArrayList<>();
        //如果  id != getParentId && getParentId == pid 则继续调用
        list.stream().filter(define -> (define.getId() != define.getParentId() && pid == define.getParentId())).forEach(define -> {
            define.setChildren(build(define.getId(), list));
            buildList.add(define);
        });
        //如果  id == getParentId ，直接添加到list中
        list.stream().filter(define -> (define.getId() == define.getParentId())).forEach(define -> buildList.add(define));
        getSort(buildList);
        return buildList;
    }
    private static void getSort(List<ProductDefine> buildList) {
        try{
            buildList.sort((o1, o2) -> {
                // 如果排序ID不存在，返回0
                if(o1.getMenuOrder() == INT0|| o2.getMenuOrder() == INT0) {
                    return INT0;
                }
                return o1.getMenuOrder() - o2.getMenuOrder();
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
