package com.weitian.dao;

import com.weitian.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018-11-08.
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String>{
    List<OrderDetail> findByOrderIdEquals(String orderId);
    List<OrderDetail> findByProductIdEquals(String productId);
}
