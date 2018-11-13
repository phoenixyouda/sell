package com.weitian.service.impl;

import com.weitian.dao.OrderDetailDao;
import com.weitian.dao.ProductInfoDao;
import com.weitian.dto.CartDTO;
import com.weitian.dto.ProductDTO;
import com.weitian.entity.OrderDetail;
import com.weitian.entity.ProductInfo;
import com.weitian.enums.ProductStatusEnum;
import com.weitian.enums.ResultEnum;
import com.weitian.exception.SellException;
import com.weitian.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoDao productInfoDao;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoDao.findById( productId ).orElse( null );
    }

    /**
     * 上架
     * @param productId
     * @return
     */
    @Override
    public ProductDTO up(String productId) {
        ProductInfo productInfo=productInfoDao.findById( productId ).orElse( null );
        if(null==productInfo){
            log.error( "【商品查询】,该商品不存在,productId={}",productId );
            throw new SellException( ResultEnum.PRODUCT_NOT_EXIST );
        }
        if(productInfo.getProductStatus().equals( ProductStatusEnum.UP.getCode() )){
            log.error( "【商品查询】,该商品已是上架状态,productId={}",productId);
            throw new SellException( ResultEnum.PRODUCT_STATUS_ERROR );
        }

        productInfo.setProductStatus( ProductStatusEnum.UP.getCode() );
        productInfo=productInfoDao.save( productInfo );
        ProductDTO productDTO=new ProductDTO();
        BeanUtils.copyProperties( productInfo,productDTO );
        return productDTO;
    }

    /**
     * 下架
     * @param productId
     * @return
     */
    @Override
    public ProductDTO down(String productId) {
        ProductInfo productInfo=productInfoDao.findById( productId ).orElse( null );
        if(null==productInfo){
            log.error( "【商品查询】,该商品不存在,productId={}",productId );
            throw new SellException( ResultEnum.PRODUCT_NOT_EXIST );
        }
        if(productInfo.getProductStatus().equals( ProductStatusEnum.DOWN.getCode() )){
            log.error( "【商品查询】,该商品已是下架状态,productId={}",productId);
            throw new SellException( ResultEnum.PRODUCT_STATUS_ERROR );
        }

        productInfo.setProductStatus( ProductStatusEnum.DOWN.getCode() );
        productInfo=productInfoDao.save( productInfo );
        ProductDTO productDTO=new ProductDTO();
        BeanUtils.copyProperties( productInfo,productDTO );
        return productDTO;
    }

    /**
     * 商品列表
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductDTO> findAll(Pageable pageable) {
        Page<ProductInfo> productPage=productInfoDao.findAll( pageable );
        if(null==productPage){
            log.info( "【商品查询】,暂无商品" );
            throw new SellException( ResultEnum.PRODUCT_IS_EMPTY );
        }
        List<ProductInfo> productInfoList=productPage.getContent();
        List<ProductDTO> productDTOList=new ArrayList<>(  );
        for(ProductInfo productInfo:productInfoList){
            ProductDTO productDTO=new ProductDTO();
            BeanUtils.copyProperties( productInfo,productDTO );
            productDTOList.add( productDTO);
        }
        PageImpl<ProductDTO> pageImpl=new PageImpl<ProductDTO>( productDTOList,pageable,productPage.getTotalElements() );
        return pageImpl;
    }
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findProductInfoByProductStatus( ProductStatusEnum.UP.getCode() );
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
