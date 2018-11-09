package com.weitian.service.impl;

import com.weitian.dao.ProductInfoDao;
import com.weitian.dto.CartDTO;
import com.weitian.entity.ProductInfo;
import com.weitian.enums.ProductInfoStatus;
import com.weitian.enums.ResultEnum;
import com.weitian.exception.SellException;
import com.weitian.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById( productId ).orElse( null );
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {

        return productInfoDao.findAll( pageable );
    }
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findProductInfoByProductStatus( ProductInfoStatus.UP.getCode() );
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoDao.save( productInfo );
    }


    //订单取消,增加库存
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        if(CollectionUtils.isEmpty( cartDTOList )){
            log.error( "【购物车为空】,cartDTO:{}" ,cartDTOList);
            new SellException( ResultEnum.CART_EMPTY );
        }
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=productInfoDao.findById( cartDTO.getProductId() ).orElse( null );
            if(productInfo==null){
                log.error("【商品不存在】,productId：{}",cartDTO.getProductId());
                new SellException( ResultEnum.PRODUCT_NOT_EXIST );
            }
            Integer result=productInfo.getProductStock()+cartDTO.getQuantity();

            productInfo.setProductStock( result );
            productInfoDao.save( productInfo );
        }
    }

    //订单创建,减少库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        if(CollectionUtils.isEmpty( cartDTOList )){
            log.error( "【购物车为空】,cartDTO:{}" ,cartDTOList);
            new SellException( ResultEnum.CART_EMPTY );
        }
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=productInfoDao.findById( cartDTO.getProductId() ).orElse( null );
            if(productInfo==null){
                log.error("【商品不存在】,productId：{}",cartDTO.getProductId());
                new SellException( ResultEnum.PRODUCT_NOT_EXIST );
            }
            Integer result=productInfo.getProductStock()-cartDTO.getQuantity();
            if(result<0){
                log.error( "【商品库存不正确】,当前库存:{},订单数量:{}",productInfo.getProductStock(),cartDTO.getQuantity() );
                new SellException( ResultEnum.PRODUCT_STOCK_ERROR );
            }
            productInfo.setProductStock( result );
            productInfoDao.save( productInfo );
        }
    }
}
