package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by K on 2017/9/12.
 */
@Configuration
public class Myconfig  extends WebMvcConfigurerAdapter{
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/chat").setViewName("/chat");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/hello").setViewName("/hello");
        registry.addViewController("/exit").setViewName("/exit");

    }
}
