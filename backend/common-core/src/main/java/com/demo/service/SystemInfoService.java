package com.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.demo.entity.SystemInfo;
import com.demo.mapper.SystemInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author gs
 * @Date 2022-08-08 15:19
 * @Description
 */
@Service
public class SystemInfoService {

    @Resource
    private SystemInfoMapper systemInfoMapper;

    /**
     * 获取系统信息
     */
    public JSONObject   getSystemConfig() {
        List<SystemInfo> systemInfoList = systemInfoMapper.selectList(null);
        JSONObject infoJO = new JSONObject();
        systemInfoList.forEach(info -> {
            infoJO.put(info.getField(), info.getData());
        });
        return infoJO;
    }

}
