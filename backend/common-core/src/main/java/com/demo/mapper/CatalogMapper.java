package com.demo.mapper;

import com.demo.entity.Catalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gs
 * @Date 2022-07-26 19:18
 * @Description 品类
 */
public interface CatalogMapper {

    /**
     * 单条插入
     * @param catalog 品类
     * @return 成功数量
     */
    int insert(Catalog catalog);

    /**
     * 单条修改
     * @param catalog 品类
     * @return 成功数量
     */
    int update(Catalog catalog);

    /**
     * 批量插入
     * @param catalogList 品类集合
     * @return 成功插入数量
     */
    int batchInsert(@Param("catalogList") List<Catalog> catalogList);

    /**
     * 批量修改
     * @param catalogList 品类集合
     * @return 成功修改数量
     */
    int batchUpdate(@Param("catalogList") List<Catalog> catalogList);

}
