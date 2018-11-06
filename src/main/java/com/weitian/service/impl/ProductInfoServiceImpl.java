package com.weitian.service.impl;

import com.weitian.dao.ProductInfoDao;
import com.weitian.entity.ProductInfo;
import com.weitian.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById( productId ).orElse( null );
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoDao.findAll();
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return productInfoDao.findAll( pageable );
    }

    @Override
    public List<ProductInfo> findProductInfoByProductStatus(Integer productStatus) {
        return productInfoDao.findProductInfoByProductStatus( productStatus );
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save( productInfo );
    }
}
