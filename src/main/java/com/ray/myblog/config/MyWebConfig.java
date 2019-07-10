package com.ray.myblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: ray
 * @create: 2019/7/8
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //归档页
        registry.addViewController("/list").setViewName("pages/list");
        //分类页
        registry.addViewController("/category").setViewName("pages/category");
        //分类对应文章列表页
        registry.addViewController("/list-by-category").setViewName("pages/list-by-category");
    }
}
