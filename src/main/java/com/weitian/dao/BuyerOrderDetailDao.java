package com.weitian.dao;

import com.weitian.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2018-11-08.
 */
public interface BuyerOrderDetailDao extends JpaRepository<OrderDetail,String>{
}
