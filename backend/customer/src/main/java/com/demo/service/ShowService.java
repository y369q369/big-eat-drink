package com.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.entity.Catalog;
import com.demo.entity.Menu;
import com.demo.mapper.CatalogMapper;
import com.demo.mapper.MenuMapper;
import com.demo.vo.MenuShowVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author gs
 * @Date 2022-07-27 10:18
 * @Description 展示层业务处理
 */
@Service
public class ShowService {

    @Resource
    private CatalogMapper catalogMapper;

    @Resource
    private MenuMapper menuMapper;

    public MenuShowVO getCatalogMenuList() {
        QueryWrapper catalogWrapper = new QueryWrapper<Catalog>();
        catalogWrapper.orderByAsc("ordinal");
        List<Catalog> catalogList = catalogMapper.selectList(catalogWrapper);

        QueryWrapper menuWrapper = new QueryWrapper<Catalog>();
        menuWrapper.orderByAsc("ordinal");
        List<Menu> menuList = menuMapper.selectList(menuWrapper);
        Map<String, List<Menu>> menuMap = menuList.stream().collect(Collectors.groupingBy(Menu :: getCatalogId));

        return new MenuShowVO(catalogList, menuMap);
    }

}
