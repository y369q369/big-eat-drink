package com.demo.mapper;

import com.demo.entity.Catalog;
import com.demo.entity.Menu;
import com.demo.util.CommonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

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
                .setName("杂项")
                .setNameEn("ZA XIANG")
                .setOrdinal(4);
        int num = catalogMapper.insert(catalog);
        assertNotEquals(0, num);

        Menu menu = new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("餐具")
                .setNameEn("CAN JU")
                .setPrice("1")
                .setOrdinal(catalog.getOrdinal() * 100 + 1);
        int num2 = menuMapper.insert(menu);
        assertNotEquals(0, num2);
    }

    @Test
    void batchInset() {
        List<Catalog> catalogList = new ArrayList<>();
        List<Menu> menuList = new ArrayList<>();

        Catalog catalog = new Catalog()
                .setId(CommonUtil.randomUUID())
                .setName("家常菜")
                .setNameEn("JIA CHANG CAI")
                .setOrdinal(1);
        catalogList.add(catalog);

        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("宫保鸡丁")
                .setNameEn("GONG BAO JI DING")
                .setPrice("12")
                .setOrdinal(catalog.getOrdinal() * 100 + 1));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("酸辣土豆丝")
                .setNameEn("SUAN LA TU DOU SI")
                .setPrice("8")
                .setOrdinal(catalog.getOrdinal() * 100 + 2));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("地三鲜")
                .setNameEn("DI SAN XIAN")
                .setPrice("11")
                .setOrdinal(catalog.getOrdinal() * 100 + 3));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("糖醋里脊")
                .setNameEn("TANG CU LI JI")
                .setPrice("15")
                .setOrdinal(catalog.getOrdinal() * 100 + 4));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("蚂蚁上树")
                .setNameEn("MA YI SHANG SHU")
                .setPrice("9")
                .setOrdinal(catalog.getOrdinal() * 100 + 5));

        Catalog catalog2 = new Catalog()
                .setId(CommonUtil.randomUUID())
                .setName("烧烤")
                .setNameEn("SHAO KAO")
                .setOrdinal(2);
        catalogList.add(catalog2);

        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("茄子")
                .setNameEn("QIE ZI")
                .setPrice("4")
                .setOrdinal(catalog2.getOrdinal() * 100 + 1));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("羊肉串")
                .setNameEn("YANG ROU CHUAN")
                .setPrice("3")
                .setOrdinal(catalog2.getOrdinal() * 100 + 2));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("五花肉")
                .setNameEn("WU HUA ROU")
                .setPrice("3")
                .setOrdinal(catalog2.getOrdinal() * 100 + 3));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("秋刀鱼")
                .setNameEn("QIU DAO YU")
                .setPrice("15")
                .setOrdinal(catalog2.getOrdinal() * 100 + 4));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("鱼皮")
                .setNameEn("YU PI")
                .setPrice("9")
                .setOrdinal(catalog2.getOrdinal() * 100 + 5));

        int num = catalogMapper.batchInsert(catalogList);
        assertNotEquals(0, num);

        int num2 = menuMapper.batchInsert(menuList);
        assertNotEquals(0, num2);
    }



}