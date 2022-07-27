package com.demo.vo;

import com.demo.entity.Catalog;
import com.demo.entity.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author gs
 * @Date 2022-07-27 10:41
 * @Description 菜单展示
 */
@Data
@AllArgsConstructor
public class MenuShowVO {

    private List<Catalog> catalogList;

    private Map<String, List<Menu>> menuMap;

}
