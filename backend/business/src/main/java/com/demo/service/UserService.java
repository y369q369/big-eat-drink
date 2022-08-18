package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.response.ResponseResult;
import com.demo.util.CommonUtil;
import com.demo.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author gs
 * @Date 2022-08-12 09:43
 * @Description 用户
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private TokenService tokenService;

    /**
     * 登录
     * @param user 登录用户
     */
    public ResponseResult login(User user, HttpServletResponse response) {
        JSONObject res = new JSONObject();
        ResponseResult responseResult = ResponseResult.fail(res);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", user.getName());
        User userData = userMapper.selectOne(queryWrapper);
        if (userData != null) {
            if (userData.getPassword().equals(SecurityUtil.md5Encrypt(user.getPassword())) ) {
                if (userData.getTryTimes() < 4) {
                    response.setHeader("token", tokenService.userLogin(user.getName()));
                    res.put("token", "登录成功");
                    responseResult = ResponseResult.success(res);
                } else {
                    res.put("msg", "今日登录失败次数超过上限");
                }
            } else {
                res.put("msg", "密码错误");
            }

            if (responseResult.status()) {
                userData.setTryTimes(0);
            } else {
                userData.setTryTimes(userData.getTryTimes() + 1);
            }
            userMapper.updateTimes(userData);
        } else {
            res.put("msg", "用户异常");
        }
        return responseResult;
    }

    /**
     * 新增用户
     * @param user 用户
     */
    public ResponseResult save(User user) {
        user.setId(CommonUtil.randomUUID());
        user.setPassword(SecurityUtil.md5Encrypt(user.getPassword()));
        int num = userMapper.insert(user);
        if (num > 0) {
            return ResponseResult.success("新增成功");
        }
        return ResponseResult.fail("新增失败");
    }

}
