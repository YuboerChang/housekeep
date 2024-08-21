package com.coolers.housekeep.housekeep.config;

import com.coolers.housekeep.housekeep.share.RequestFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<Filter> requestFilterBean(RequestFilter requestFilter) {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(requestFilter);
        filterRegistrationBean.addUrlPatterns("/login");
        return filterRegistrationBean;
    }
}
