package com.demo.controller;

import com.demo.entity.User;
import com.demo.response.BaseResponse;
import com.demo.response.ResponseResult;
import com.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author gs
 * @Date 2022-08-12 09:41
 * @Description 用户
 */
@BaseResponse
@RequestMapping("user")
@Api(tags = "用户")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public ResponseResult login(@RequestBody User user, HttpServletResponse response) {
        return userService.login(user, response);
    }

    @ApiOperation(value = "新增")
    @PostMapping("user")
    public ResponseResult save(@RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation(value = "注销")
    @DeleteMapping("logout")
    public ResponseResult logout(@RequestParam("userName") String userName) {
        return userService.logout(userName);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("userInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名称", example = "admin", dataType = "String", paramType = "query", required = true)
    })
    public ResponseResult getUserInfo(@RequestParam("userName") String userName) {
        return userService.getUserInfo(userName);
    }

}
