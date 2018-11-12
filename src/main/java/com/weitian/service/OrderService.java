package com.weitian.service;

import com.weitian.dto.OrderDTO;
import com.weitian.entity.OrderMaster;
import com.weitian.utils.ResultVOUtil;
import com.weitian.vo.ResultVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Map;

/**
 * Created by Administrator on 2018-11-08.
 */
public interface OrderService {

    //增加订单
    public OrderDTO create(OrderDTO orderDTO);
    //查询订单
    public Page<OrderDTO> findAll(Pageable pageable);
    //支付订单
    //修改库存
    //取消订单
}
