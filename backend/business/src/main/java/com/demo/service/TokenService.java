package com.demo.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo.dto.TokenDto;
import com.demo.enums.TokenEnum;
import com.demo.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author gs
 * @Date 2022-08-18 10:46
 * @Description token 相关操作
 */
@Service
@Slf4j
public class TokenService {

    private Map<String, List<String>> tokenMap = new HashMap<>();

    /**
     * 获取登录令牌
     * @param userName 用户名
     */
    public String userLogin(String userName) {
        return getToken(userName, TokenEnum.TYPE_LOGIN, TokenEnum.LOGIN_EXPIRE);
    }

    /**
     * 获取长期令牌
     * @param name key
     * @param expireDay 过期天数
     * @return
     */
    public String getPermanentToken(String name, int expireDay) {
        return getToken(name, TokenEnum.TYPE_PERMANENT, expireDay);
    }

    /**
     * 创建令牌, 如果存在 有效时长 超过 总时长一半 的token，使用旧token，否则使用新token
     * @param name key
     * @param type 类型
     * @param expireDay 过期天数
     */
    public String getToken(String name, String type, int expireDay) {
        String token = getOldToken(name);
        if (token == null) {
            TokenDto tokenDto = new TokenDto();
            tokenDto.setName(name);
            tokenDto.setType(type);
            tokenDto.setExpireDay(expireDay);
            token = JWTUtil.createToken(tokenDto);
            updateToken(name, token);
        }
        return token;
    }

    /**
     * 获取 有效时长 超过 总时长一半 的旧token
     * @param name key
     */
    public String getOldToken (String name) {
        List<String> tokenList = tokenMap.get(name);
        if (tokenList != null && tokenList.size() > 0) {
            // 获取最近一个生成的token
            String token = tokenList.get(tokenList.size() - 1);
            try {
                DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
                // token剩余有效时长超过一半，则返回
                if (decodedJWT.getExpiresAt().getTime() - new Date().getTime()  > decodedJWT.getClaim("time").asLong() / 2) {
                    return token;
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 更新token
     */
    public void updateToken(String key, String token) {
        List<String> tokenList = tokenMap.computeIfAbsent(key, v -> new ArrayList<>());
        handleExpireToken(tokenList);
        tokenList.add(token);
    }

    /**
     * 清除过期token
     * @param tokenCol token对应的集合
     */
    public void handleExpireToken(Collection<String> tokenCol) {
        Iterator<String> it = tokenCol.iterator();
        while (it.hasNext()) {
            String oldToken = it.next();
            // 清除过期token
            try {
                JWTUtil.verifyToken(oldToken);
            } catch (Exception e) {
                it.remove();
                log.info("清除过期token: {}", oldToken);
            }
        }
    }

    /**
     * 检查token是否存在
     * @param name key
     * @param token token
     * @return
     */
    public boolean isExistToken(String name, String token) {
        List<String> tokenList = tokenMap.get(name);
        if (tokenList != null) {
            return token.contains(token);
        }
        return false;
    }

    /**
     * 删除token
     * @param name token对应的key
     */
    public void deleteToken(String name) {
        tokenMap.remove(name);
    }


//    /**
//     * token延期
//     * @param key
//     * @param token
//     */
//    public void delayToken(String key, String token) {
//        DecodedJWT decodedJWT = JWTUtil.verifyToken(token);
//        List<String> tokenList = tokenMap.get(key);
//        if (new Date().getTime() - decodedJWT.getExpiresAt().getTime() > 100) {
//            int index = tokenList.indexOf(token);
//            tokenList.remove(index);
//        } else {
//
//        }
//    }


}
