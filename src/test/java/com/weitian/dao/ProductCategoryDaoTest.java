package com.weitian.dao;

import com.weitian.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018-11-06.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {
    @Autowired
    private ProductCategoryDao dao;

    @Test
    public void save(){
        ProductCategory p=new ProductCategory();
        p.setCategoryType(2);
        p.setCategoryName("2");
        dao.save(p);

    }
    @Test
    public void query(){
        List<ProductCategory> list=dao.findAll();
        System.out.println(list);

    }
    @Test
    public void queryByType(){
        List<Integer> types= Arrays.asList(1,2,3,4);
        List<ProductCategory> list=dao.findByCategoryType(types);
        System.out.println(list.size());

    }
}