package com.fjy.spring.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/home/**");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/home/admin/**");
    }

    /**
     * 排除静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cms/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/cms/css/**").addResourceLocations("classpath:/css/");
        //registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/cms/fonts/**").addResourceLocations("classpath:/fonts/");
        registry.addResourceHandler("/cms/images/**").addResourceLocations("classpath:/images/");
    }
}
