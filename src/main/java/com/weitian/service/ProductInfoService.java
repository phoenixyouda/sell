package com.weitian.service;

import com.weitian.dto.CartDTO;
import com.weitian.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
public interface ProductInfoService {
    public ProductInfo findOne(String productId);
    public Page<ProductInfo> findAll(Pageable pageable);
    public List<ProductInfo> findUpAll();
    public ProductInfo save(ProductInfo productInfo);
    //增加库存
    public void increaseStock(List<CartDTO> cartDTO);
    //减少库存
    public void decreaseStock(List<CartDTO> cartDTO);
}
