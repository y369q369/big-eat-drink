package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.response.ResponseResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author gs
 * @Date 2022-08-08 15:28
 * @Description
 */
@Service
public class SystemService {

    @Resource
    private SystemInfoService systemInfoService;

    public ResponseResult getSystemConfig() {
        JSONObject info = systemInfoService.getSystemConfig();
        return ResponseResult.success(info);
    }

}
