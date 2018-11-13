package com.weitian.service;

import com.weitian.dto.OrderDTO;
import com.weitian.entity.OrderMaster;
import com.weitian.utils.ResultVOUtil;
import com.weitian.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;


import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018-11-08.
 */
public interface OrderService {

    //增加订单
    public OrderDTO create(OrderDTO orderDTO);
    //查询订单
    public Page<OrderDTO> findAll(Pageable pageable);

    OrderDTO findByOrderId(String orderId);
    //完结订单
    OrderDTO finish(String orderId);

    //取消订单
    OrderDTO cancel(String OrderId);
}
