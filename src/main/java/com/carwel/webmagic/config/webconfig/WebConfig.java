package com.carwel.webmagic.config.webconfig;

import com.carwel.webmagic.config.filter.JsonParamsHolderFilter;
import com.carwel.webmagic.config.resolver.RequestJsonParamMethodArgumentResolver;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;


@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        argumentResolvers.add(getJsonParamResolver());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public HandlerMethodArgumentResolver getJsonParamResolver() {
        RequestJsonParamMethodArgumentResolver requestJsonParamMethodArgumentResolver = new RequestJsonParamMethodArgumentResolver(
                true);
        return requestJsonParamMethodArgumentResolver;
    }
    @Bean
    public FilterRegistrationBean jsonParamsHolderFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JsonParamsHolderFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("jsonParamsHolderFilter");
        registrationBean.setOrder(3);
        return registrationBean;
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        // 解决中文乱码问题
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }
}
