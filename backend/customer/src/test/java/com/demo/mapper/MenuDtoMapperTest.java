package com.demo.mapper;

import com.demo.entity.Catalog;
import com.demo.entity.Menu;
import com.demo.util.CommonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigDecimal;
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
                .setOrdinal(9);
        int num = catalogMapper.insert(catalog);
        assertNotEquals(0, num);

        Menu menu = new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("餐具")
                .setNameEn("CAN JU")
                .setPrice(new BigDecimal("1"))
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
                .setPrice(new BigDecimal("12"))
                .setOrdinal(catalog.getOrdinal() * 100 + 1));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("酸辣土豆丝")
                .setNameEn("SUAN LA TU DOU SI")
                .setPrice(new BigDecimal("8"))
                .setOrdinal(catalog.getOrdinal() * 100 + 2));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("地三鲜")
                .setNameEn("DI SAN XIAN")
                .setPrice(new BigDecimal("11"))
                .setOrdinal(catalog.getOrdinal() * 100 + 3));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("糖醋里脊")
                .setNameEn("TANG CU LI JI")
                .setPrice(new BigDecimal("15"))
                .setOrdinal(catalog.getOrdinal() * 100 + 4));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog.getId())
                .setName("蚂蚁上树")
                .setNameEn("MA YI SHANG SHU")
                .setPrice(new BigDecimal("9"))
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
                .setPrice(new BigDecimal("4"))
                .setOrdinal(catalog2.getOrdinal() * 100 + 1));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("羊肉串")
                .setNameEn("YANG ROU CHUAN")
                .setPrice(new BigDecimal("3"))
                .setOrdinal(catalog2.getOrdinal() * 100 + 2));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("五花肉")
                .setNameEn("WU HUA ROU")
                .setPrice(new BigDecimal("3"))
                .setOrdinal(catalog2.getOrdinal() * 100 + 3));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("秋刀鱼")
                .setNameEn("QIU DAO YU")
                .setPrice(new BigDecimal("15"))
                .setOrdinal(catalog2.getOrdinal() * 100 + 4));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("鱼皮")
                .setNameEn("YU PI")
                .setPrice(new BigDecimal("9"))
                .setOrdinal(catalog2.getOrdinal() * 100 + 5));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("鸡翅")
                .setNameEn("JI CHI")
                .setPrice(new BigDecimal("4"))
                .setOrdinal(catalog2.getOrdinal() * 100 + 6));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog2.getId())
                .setName("鹌鹑")
                .setNameEn("AN CHUN")
                .setPrice(new BigDecimal("10"))
                .setOrdinal(catalog2.getOrdinal() * 100 + 7));

        Catalog catalog3 = new Catalog()
                .setId(CommonUtil.randomUUID())
                .setName("饮品")
                .setNameEn("YIN PIN")
                .setOrdinal(3);
        catalogList.add(catalog3);

        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog3.getId())
                .setName("怡宝")
                .setNameEn("YI BAO")
                .setPrice(new BigDecimal("1"))
                .setOrdinal(catalog3.getOrdinal() * 100 + 1));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog3.getId())
                .setName("可乐")
                .setNameEn("KE LE")
                .setPrice(new BigDecimal("2"))
                .setOrdinal(catalog3.getOrdinal() * 100 + 2));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog3.getId())
                .setName("雪碧")
                .setNameEn("XUE BI")
                .setPrice(new BigDecimal("3"))
                .setOrdinal(catalog3.getOrdinal() * 100 + 3));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog3.getId())
                .setName("加多宝")
                .setNameEn("JIA DUO BAO")
                .setPrice(new BigDecimal("4"))
                .setOrdinal(catalog3.getOrdinal() * 100 + 4));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog3.getId())
                .setName("椰汁")
                .setNameEn("YE ZHI")
                .setPrice(new BigDecimal("10"))
                .setOrdinal(catalog3.getOrdinal() * 100 + 5));
        menuList.add(new Menu()
                .setId(CommonUtil.randomUUID())
                .setCatalogId(catalog3.getId())
                .setName("果汁")
                .setNameEn("GUO ZHI")
                .setPrice(new BigDecimal("15"))
                .setOrdinal(catalog3.getOrdinal() * 100 + 6));

        int num = catalogMapper.batchInsert(catalogList);
        assertNotEquals(0, num);

        int num2 = menuMapper.batchInsert(menuList);
        assertNotEquals(0, num2);
    }



}