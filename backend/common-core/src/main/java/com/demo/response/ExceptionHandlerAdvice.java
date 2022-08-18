package com.demo.response;

import com.demo.exception.BaseException;
import com.demo.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author gs
 * @Date 2021-10-28 16:04
 * @Description 自定义统一异常处理
 */
@ControllerAdvice(annotations = BaseResponse.class)
@ResponseBody
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * 处理未捕获的Exception
     * @param e 异常
     * @return 统一响应体
     * data:null
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e){
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR, e.getMessage());
    }

    /**
     * 处理未捕获的RuntimeException
     * @param e 运行异常
     * @return 统一响应体
     * data:null
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult handleRuntimeException(RuntimeException e){
        log.error(e.getMessage(), e);
        return new ResponseResult(ResponseCode.SERVICE_ERROR, e.getMessage());
    }

    /**
     * 处理业务异常BaseException
     * @param e 业务异常
     * @return 统一响应体
     * data:null
     */
    @ExceptionHandler(BaseException.class)
    public ResponseResult handleBaseException(BaseException e){
        log.error("{}访问 : {}", IpUtil.getRequestIp(), e.getMessage(), e);
        ResponseResult responseResult = new ResponseResult(e.getCode(), e.getMessage());
        return responseResult;
    }

}
