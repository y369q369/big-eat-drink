package com.demo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author gs
 * @Date 2021-10-28 16:19
 * @Description 公共响应体
 */
@Data
@NoArgsConstructor
public class ResponseResult {

    /**
     * 返回的状态码
     */
    private Integer code;

    /**
     * 返回的信息
     */
    private String msg;

    /**
     * 返回的数据
     */
    private Object data;

    public ResponseResult(ResponseCode responseCode, Object data) {
        this.code = responseCode.getCode();
        this.msg = responseCode.getMsg();
        this.data = data;
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(ResponseCode.SUCCESS, data);
    }

    public static ResponseResult fail(Object data) {
        return new ResponseResult(ResponseCode.FAIL, data);
    }

    /**
     * 判断是否成功
     * @return true: 成功  false: 失败
     */
    public boolean status() {
        if (code == 200) {
            return true;
        }
        return false;
    }
}
