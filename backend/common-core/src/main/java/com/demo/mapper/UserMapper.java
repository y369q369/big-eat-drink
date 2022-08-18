package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author gs
 * @Date 2022-08-11 16:40
 * @Description 系统用户
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 更新用户尝试次数
     * @param user 用户
     */
    int updateTimes(User user);


}
