package cn.com.yusys.yusp.example.common.config;

import co.elastic.apm.servlet.ApmFilter;
import co.elastic.apm.spring.webmvc.ApmHandlerInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by yzq on 2018/4/18.
 */
@Configuration
public class ApmConfig extends WebMvcConfigurerAdapter {

    @Bean
    public FilterRegistrationBean apmFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new ApmFilter());
        registration.addUrlPatterns("/*");
        registration.setName("apmFilter");
        registration.setOrder(1);
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApmHandlerInterceptor());
    }
}
