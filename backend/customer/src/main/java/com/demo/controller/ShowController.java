package com.demo.controller;

import com.demo.service.ShowService;
import com.demo.vo.MenuShowVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author gs
 * @Date 2022-07-27 10:16
 * @Description 顾客层展示controller
 */
@RestController
@RequestMapping("show")
@Api(tags = "展示")
public class ShowController {

    @Resource
    private ShowService showService;

    @ApiOperation(value = "种类 - 菜单 列表")
    @GetMapping("catalogMenuList")
    public MenuShowVO getCatalogMenuList() {
        return showService.getCatalogMenuList();
    }
}
