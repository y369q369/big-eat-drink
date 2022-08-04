package com.demo.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Author gs
 * @Date 2021-10-28 16:36
 * @Description 统一响应体处理器--响应增强类， 只处理自定义注解， 如需要全部处理，则 annotations = RestController.class
 *              允许在执行 {@link ResponseBody} 或 {@link ResponseEntity} 控制器方法之后，但在使用{@link HttpMessageConverter}编写主体之前自定义响应。
 *              实现可以直接用{@link RequestMappingHandlerAdapter} 和 {@code exceptionHandle exceptionResolver}注册，或者更可能用{@link ControllerAdvice}注释，在这种情况下，它们将被两者自动检测。
 *              参考： https://www.cnblogs.com/goloving/p/15045736.html
 */
@ControllerAdvice(annotations = BaseResponse.class)
@Slf4j
public class ResponseResultHandlerAdvice implements ResponseBodyAdvice<Object> {

    // 如果接口返回的类型本身就是统一响应体的格式，那就没有必要进行额外的操作，返回true
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        log.debug("returnType: {}", returnType);
        log.debug("converterType: {}", converterType);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 判断响应的Content-Type为JSON格式的body
        if(MediaType.APPLICATION_JSON.equals(selectedContentType)){
            // 如果响应返回的对象为统一响应体，则直接返回body
            if(body instanceof ResponseResult){
                return body;
            }else{
                // 只有正常返回的结果才会进入这个判断流程，返回正常成功的状态码+信息+数据。
                return new ResponseResult(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), body);
            }
        }
        // 非JSON格式body直接返回
        return body;
    }
}
