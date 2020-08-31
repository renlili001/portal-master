package com.tydic.jg.portal.utils;

import com.tydic.jg.authentication.HttpSecurityConfigure;
import com.tydic.jg.authentication.WebSecurityConfigure;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class GlobalConfig {

    @Bean
    public HttpSecurityConfigure httpSecurityConfigure() {
        return http -> http.csrf().ignoringAntMatchers("/**");
    }

    @Bean
    public WebSecurityConfigure webSecurityConfigure() {
        return web -> web.ignoring()
                .antMatchers("/error")
                .antMatchers("/swagger-ui.html**")
                .antMatchers("/webjars/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/**");
    }

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tydic.jg.portal"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("门户系统")
                .description("门户系统接口文档")
                .version("1.0")
                .build();
    }
}
