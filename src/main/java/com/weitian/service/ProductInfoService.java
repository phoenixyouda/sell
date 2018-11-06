package com.weitian.service;

import com.weitian.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
public interface ProductInfoService {
    public ProductInfo findOne(String productId);
    public List<ProductInfo> findAll();
    public Page<ProductInfo> findAll(Pageable pageable);
    public List<ProductInfo> findProductInfoByProductStatus(Integer productStatus);
    public ProductInfo save(ProductInfo productInfo);
}
