package com.weitian.convert;

import com.weitian.dto.CartDTO;
import com.weitian.entity.OrderDetail;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018-11-13.
 */
public class OrderDetail2CatDTOConverter {
    public static CartDTO convert(OrderDetail orderDetail){
        CartDTO cartDTO=new CartDTO( orderDetail.getProductId(),orderDetail.getProductQuantity() );
        return cartDTO;
    }
    public static List<CartDTO> convert(List<OrderDetail> orderDetailList){
        return orderDetailList.stream().map( e->convert( e ) ).collect( Collectors.toList() );
    }
}
