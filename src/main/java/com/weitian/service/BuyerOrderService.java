package com.weitian.service;

import com.weitian.dto.OrderDTO;
import com.weitian.utils.ResultVOUtil;
import com.weitian.vo.ResultVO;

import java.util.Map;

/**
 * Created by Administrator on 2018-11-08.
 */
public interface BuyerOrderService {

    //增加订单
    public OrderDTO create(OrderDTO orderDTO);
    //查询订单
    //支付订单
    //修改库存
    //取消订单
}
