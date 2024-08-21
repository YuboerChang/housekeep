package com.coolers.housekeep.housekeep.config;

import com.coolers.housekeep.housekeep.share.WebInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getWebInterceptor()).addPathPatterns("/*").excludePathPatterns("/login");
        //便于测试，不开拦截
        registry.addInterceptor(getWebInterceptor()).addPathPatterns("");
    }

    @Bean
    public WebInterceptor getWebInterceptor() {
        return new WebInterceptor();
    }
}
