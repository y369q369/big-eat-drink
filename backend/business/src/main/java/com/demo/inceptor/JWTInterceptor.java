package com.demo.inceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.config.BeanConfig;
import com.demo.enums.ResponseEnum;
import com.demo.exception.BaseException;
import com.demo.response.ResponseCode;
import com.demo.service.TokenService;
import com.demo.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author gs
 * @Date 2022-08-17 15:08
 * @Description
 */
@Slf4j
public class JWTInterceptor implements HandlerInterceptor {

    private TokenService tokenService = BeanConfig.getBean(TokenService.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWTUtil.verifyToken(token);
        } catch (SignatureVerificationException e) {
            throw new BaseException(ResponseEnum.Token.VERIFICATION_MMISMATCH, ResponseCode.TOKEN_ERROR, e);
        } catch (TokenExpiredException e) {
            throw new BaseException(ResponseEnum.Token.EXPIRE, ResponseCode.TOKEN_ERROR, e);
        } catch (AlgorithmMismatchException e) {
            throw new BaseException(ResponseEnum.Token.ALGORITHM_ERROR, ResponseCode.TOKEN_ERROR, e);
        } catch (Exception e) {
            throw new BaseException(ResponseEnum.Token.ERROR, ResponseCode.TOKEN_ERROR, e);
        }

        String name = decodedJWT.getClaim("name").asString();
        if (tokenService.isExistToken(name, token)) {
            // 有效时长小于一半的更换新token
            if (decodedJWT.getExpiresAt().getTime() - new Date().getTime() < decodedJWT.getClaim("time").asLong() / 2) {
                String newToken = tokenService.userLogin(decodedJWT.getClaim("name").asString());
                response.setHeader("token", newToken);
            }
        } else {
            throw new BaseException(ResponseEnum.Token.NOT_EXIST, ResponseCode.TOKEN_ERROR);
        }
        return true;
    }
}
