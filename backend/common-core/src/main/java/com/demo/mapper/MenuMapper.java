package com.demo.mapper;

import com.demo.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gs
 * @Date 2022-07-26 19:17
 * @Description 菜单
 */
public interface MenuMapper {

    /**
     * 单条插入
     * @param menu 菜单
     * @return 成功数量
     */
    int insert(Menu menu);

    /**
     * 单条修改
     * @param menu 菜单
     * @return 成功数量
     */
    int update(Menu menu);

    /**
     * 批量插入
     * @param list 菜单集合
     * @return 成功插入数量
     */
    int batchInsert(@Param("list") List<Menu> list);

    /**
     * 批量修改
     * @param list 菜单集合
     * @return 成功修改数量
     */
    int batchUpdate(@Param("list") List<Menu> list);

}
