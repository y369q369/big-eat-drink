package com.demo.controller;

import com.demo.entity.AppUser;
import com.demo.response.ResponseResult;
import com.demo.service.AppUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author gs
 * @Date 2022-08-09 14:51
 * @Description 小程序用户
 */
@RestController
@RequestMapping("appUser")
@Api(tags = "小程序用户")
public class AppUserController {

    @Resource
    private AppUserService appUserService;

    @ApiOperation(value = "新增用户")
    @PostMapping("user")
    public ResponseResult saveUser(@RequestBody AppUser appUser) {
        return appUserService.saveUser(appUser);
    }

    @ApiOperation(value = "获取用户")
    @GetMapping("user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openid", value = "用户标识", example = "12345", dataType = "String", paramType = "query", required = true),
    })
    public ResponseResult getUser(@RequestParam("openid") String openid) {
        return appUserService.getUser(openid);
    }

}
