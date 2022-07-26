package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gs
 * @Date 2022-07-26 19:17
 * @Description 菜单
 */
public interface MenuMapper extends BaseMapper<Menu> {

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
