package com.tydic.jg.portal.system;

import com.tydic.jg.portal.system.jpa.repository.*;
import com.tydic.jg.portal.system.svc.*;
import com.tydic.jg.portal.system.svc.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SystemConfigure {

    @Bean
    public MenuInfoService menuInfoService(MenuInfoRepository menuInfoRepository,MenuRolesRepository menuRolesRepository,SystemInfoRepository systemInfoRepository){
        return new MenuInfoServiceImpl(menuInfoRepository,menuRolesRepository,systemInfoRepository);
    }
    @Bean
    public PageDefineService pageDefineService(PageDefineRepository pageDefineRepository){
        return new PageDefineServiceImpl(pageDefineRepository);
    }

    @Bean
    public ProductDefineService productDefineService(ProductDefineRepository productDefineRepository){
        return new ProductDefineServiceImpl(productDefineRepository);
    }
    @Bean
    public ProductInfoService productInfoService(ProductInfoRepository productInfoRepository){
        return new ProductInfoServiceImpl(productInfoRepository);
    }
    @Bean
    public SystemInfoService systemInfoService(SystemInfoRepository systemInfoRepository){
        return new SystemInfoServiceImpl(systemInfoRepository);
    }
}
