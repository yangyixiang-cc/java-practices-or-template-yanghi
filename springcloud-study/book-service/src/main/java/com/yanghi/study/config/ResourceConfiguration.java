package com.yanghi.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceConfiguration extends ResourceServerConfigurerAdapter { //继承此类进行高度自定义

    @Override
    public void configure(HttpSecurity http) throws Exception {  //这里也有HttpSecurity对象，方便我们配置SpringSecurity
        http
                .authorizeRequests()
                .anyRequest().access("#oauth2.hasScope('book')");  //添加自定义规则
      					//Token必须要有我们自定义scope授权才可以访问此资源
    }
}