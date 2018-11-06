package com.weitian.dao;

import com.weitian.entity.ProductInfo;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018-11-06.
 */
public interface ProductInfoDao extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findProductInfoByProductStatus(Integer productStatus);


}
