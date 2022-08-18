package com.demo.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author gs
 * @Date 2022-08-18 09:49
 * @Description
 */
@Aspect
//@Component
@Slf4j
public class TokenAop {




    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    @Pointcut("execution(public * com.demo.controller.*.*(..))")
    public void token() {
        log.info("token 123");
    }

    @Before("token()")
    public void tokenBefore(JoinPoint point) {
        log.info("前置通知");
        String token = request.getHeader("token");

//        Map<String, String>
        DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
        String userName = decodedJWT.getClaim("name").asString();
        Date expire= decodedJWT.getExpiresAt();


        log.info(token);
    }

    @After("token()")
    public void after(JoinPoint point) {
        log.info("后置通知");
    }

}
