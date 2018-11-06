package com.weitian.service.impl;

import com.weitian.dao.ProductCategoryDao;
import com.weitian.entity.ProductCategory;
import com.weitian.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryDao.findById( categoryId ).orElse( null );
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn( categoryTypeList );
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save( productCategory );
    }
}
