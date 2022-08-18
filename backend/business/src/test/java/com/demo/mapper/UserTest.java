package com.demo.mapper;

import com.demo.entity.User;
import com.demo.util.CommonUtil;
import com.demo.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@DisplayName("系统用户测试类")
@Slf4j
class UserTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void insertUser() {
        User user = new User()
                .setId(CommonUtil.randomUUID())
                .setName("admin")
                .setPassword(SecurityUtil.md5Encrypt("admin"))
                .setAge(18)
                .setSex(1)
                .setTryTimes(0);
        int insertNum = userMapper.insert(user);
        assertNotEquals(0, insertNum);
        log.info("新增用户 admin 成功");
    }



}