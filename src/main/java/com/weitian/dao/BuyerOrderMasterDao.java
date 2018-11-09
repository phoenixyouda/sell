package com.weitian.dao;

import com.weitian.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by Administrator on 2018-11-08.
 */
public interface BuyerOrderMasterDao extends JpaRepository<OrderMaster,String>{
    Page<OrderMaster> findByBuyerOpenId(String openId, Pageable pageable);
}
