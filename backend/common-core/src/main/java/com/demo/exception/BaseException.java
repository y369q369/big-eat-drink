package com.demo.exception;

import com.demo.response.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author gs
 * @Date 2021-10-28 16:28
 * @Description 自定义异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException {

    // 枚举对象
    private ResponseCode code;

    public BaseException(ResponseCode code) {
        this.code = code;
    }

    public BaseException(ResponseCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseException(String message, ResponseCode code) {
        super(message);
        this.code = code;
    }

    public BaseException(String message, ResponseCode code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
