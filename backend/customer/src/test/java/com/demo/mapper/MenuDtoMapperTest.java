package com.demo.mapper;

import com.demo.entity.Catalog;
import com.demo.entity.Menu;
import com.demo.util.CommonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("种类菜单综合测试类")
class MenuDtoMapperTest {

    @Resource
    private CatalogMapper catalogMapper;

    @Resource
    private MenuMapper menuMapper;

    @Test
    void insertCatalogAndMenu() {
        Catalog catalog = new Catalog()
                .setId(CommonUtil.randomUUID())
                .setName("家常菜")
                .setNameEn("JIA CHANG CAI")
                .setOrdinal(1);
        int num = catalogMapper.insert(catalog);
        assertNotEquals(0, num);

        Menu menu = new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("宫保鸡丁")
                .setNameEn("GONG BAO JI DING")
                .setPrice("12")
                .setOrdinal(1);
        int num2 = menuMapper.insert(menu);
        assertNotEquals(0, num2);
    }

    @Test
    void getMenuList() {
    }



}