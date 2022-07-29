package com.demo.mapper;

import com.demo.dto.MenuDto;

import java.util.List;

public interface MenuDtoMapper {

    /**
     * 获取所有种类和菜单
     */
    List<MenuDto> getMenuList();

    

}
