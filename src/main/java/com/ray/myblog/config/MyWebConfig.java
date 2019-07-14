package com.ray.myblog.config;

import com.ray.myblog.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
        //添加文章
        registry.addViewController("/back/add-article").setViewName("back/add-article");
        //管理员登录
        registry.addViewController("/back/login-page").setViewName("back/login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/article-images/**").addResourceLocations("file:/D:/Blog Resources/images/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/dashboard", "/back/**")
                .excludePathPatterns("/back/login")
                .excludePathPatterns("/back/login-page");
    }
}
