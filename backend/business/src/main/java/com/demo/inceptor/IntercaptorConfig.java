package com.demo.inceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author gs
 * @Date 2022-08-17 16:31
 * @Description
 */
@Configuration
public class IntercaptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 不限制访问路径集合
        List<String> excludePatterns = new ArrayList<>();
        // swagger-api相关
        excludePatterns.addAll(Arrays.asList("/doc.html", "/webjars/**", "/swagger-resources", "/v2/api-docs", "/doc.html"));
        // 登录
        excludePatterns.add("/user/login");

        registry.addInterceptor(new JWTInterceptor())
                //拦截的路径
                .addPathPatterns("/**")
                //排除接口
                .excludePathPatterns(excludePatterns);
    }

}
