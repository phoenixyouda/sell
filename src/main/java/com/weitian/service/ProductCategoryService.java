package com.weitian.service;

import com.weitian.entity.ProductCategory;

import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
public interface ProductCategoryService {
    public ProductCategory findOne(Integer categoryId);
    public List<ProductCategory> findAll();
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    public ProductCategory save(ProductCategory productCategory);
}
