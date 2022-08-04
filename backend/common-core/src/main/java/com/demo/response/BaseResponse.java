package com.demo.response;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author gs
 * @Date 2021-10-28 16:30
 * @Description 统一响应注解--自定义注解。
 *               添加注解后，实现 {@link ResponseBodyAdvice}， 统一处理页面响应体
 *               controller 中直接使用 {@link BaseResponse}， 不需要再额外添加 {@link RestController}
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@RestController
public @interface BaseResponse {
}
