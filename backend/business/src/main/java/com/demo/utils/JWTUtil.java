package com.demo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.dto.TokenDto;

import java.util.Calendar;

/**
 * @Author gs
 * @Date 2022-08-11 18:02
 * @Description jwt工具类， 参考：https://blog.csdn.net/qq_47183158/article/details/123973732
 */
public class JWTUtil {

    private static String SALT = "salt";

    /**
     * 生成token
     */
    public static String createToken(TokenDto tokenDto) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, tokenDto.getExpireDay());
        String token = JWT.create()
                .withExpiresAt(calendar.getTime())              // 设置 过期时间
                .withClaim("name", tokenDto.getName())    // 设置payload 非敏感值
                .withClaim("type", tokenDto.getType())    // 设置payload 非敏感值
                .withClaim("time", (long)tokenDto.getExpireDay() * 24 * 60 * 60 * 1000)    // 设置 过期时长
                .sign(Algorithm.HMAC256(SALT));                 // 设置密匙
        return token;
    }

    /**
     * 解析token
     */
    public static DecodedJWT verifyToken(String token) {
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SALT)).build();
        return build.verify(token);
    }

}
