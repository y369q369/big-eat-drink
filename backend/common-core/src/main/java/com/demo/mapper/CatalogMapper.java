package com.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Catalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author gs
 * @Date 2022-07-26 19:18
 * @Description 品类
 */
public interface CatalogMapper extends BaseMapper<Catalog> {

    /**
     * 批量插入
     * @param catalogList 品类集合
     * @return 成功插入数量
     */
    int batchInsert(@Param("list") List<Catalog> catalogList);

    /**
     * 批量修改
     * @param catalogList 品类集合
     * @return 成功修改数量
     */
    int batchUpdate(@Param("list") List<Catalog> catalogList);

}
