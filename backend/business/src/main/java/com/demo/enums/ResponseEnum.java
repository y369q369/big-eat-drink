package com.demo.enums;

/**
 * @Author gs
 * @Date 2022-08-18 11:30
 * @Description 响应信息枚举
 */
public interface ResponseEnum {

    interface Token {
        String VERIFICATION_MMISMATCH = "token无效签名";

        String EXPIRE = "token过期";

        String ALGORITHM_ERROR = "token算法异常";

        String NOT_EXIST = "token不存在";

        String ERROR = "token异常";
    }
    
}
