package com.galaxy.config;

import com.galaxy.filter.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class FilterConfig {

    @Autowired
    private MyFilter myFilter;

    @Bean
    public FilterRegistrationBean<MyFilter> filter() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(myFilter);
        registrationBean.setUrlPatterns(new ArrayList<>(Arrays.asList("/api/*")));  // 过滤掉
        return registrationBean;
    }
}
