package com.weitian.convert;

import com.weitian.dto.OrderDTO;
import com.weitian.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018-11-12.
 */
public class OrderMaster2OrderDTOConvert {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties( orderMaster,orderDTO );
        return orderDTO;
    }
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map( e->convert(e) ).collect( Collectors.toList() );
    }
}
