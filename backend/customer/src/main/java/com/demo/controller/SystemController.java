package com.demo.controller;

import com.demo.response.ResponseResult;
import com.demo.service.SystemService;
import com.demo.vo.MenuShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author gs
 * @Date 2022-08-08 15:23
 * @Description 系统信息
 */
@RestController
@RequestMapping("system")
@Api(tags = "系统")
public class SystemController {

    @Resource
    private SystemService systemService;

    @ApiOperation(value = "配置信息")
    @GetMapping("systemConfig")
    public ResponseResult getSystemConfig() {
        return systemService.getSystemConfig();
    }

}
