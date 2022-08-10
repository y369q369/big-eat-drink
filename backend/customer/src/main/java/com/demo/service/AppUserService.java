package com.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.entity.AppUser;
import com.demo.mapper.AppUserMapper;
import com.demo.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author gs
 * @Date 2022-08-09 14:52
 * @Description 小程序用户
 */
@Service
@Slf4j
public class AppUserService {

    @Resource
    private AppUserMapper appUserMapper;

    public ResponseResult saveUser(AppUser appUser) {
        int insertNum = appUserMapper.insert(appUser);
        log.info("【小程序用户】新增 {} 条成功", insertNum);
        return ResponseResult.success(insertNum);
    }

    public ResponseResult getUser(String openid) {
        QueryWrapper<AppUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        AppUser appUser = appUserMapper.selectOne(queryWrapper);
        if (appUser == null)
            appUser = new AppUser();
        return ResponseResult.success(appUser);
    }
}
