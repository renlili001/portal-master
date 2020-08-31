package com.tydic.jg.portal.menu;

import com.tydic.jg.portal.menu.jpa.repository.MenuRepository;
import com.tydic.jg.portal.menu.jpa.repository.SysRolersMenusRepository;
import com.tydic.jg.portal.menu.jpa.repository.SysSubRepository;
import com.tydic.jg.portal.menu.svc.MenuService;
import com.tydic.jg.portal.menu.svc.SysSubService;
import com.tydic.jg.portal.menu.svc.impl.MenuServiceImpl;
import com.tydic.jg.portal.menu.svc.impl.SysSubServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configure {

    @Bean
    public MenuService menuService(MenuRepository menuRepository, SysRolersMenusRepository sysRolersMenusRepository){
        return new MenuServiceImpl(menuRepository,sysRolersMenusRepository);
    }
    @Bean
    public SysSubService subsysService(SysSubRepository sysSubRepository,MenuRepository menuRepository){
        return new SysSubServiceImpl(sysSubRepository,menuRepository);
    }

}
