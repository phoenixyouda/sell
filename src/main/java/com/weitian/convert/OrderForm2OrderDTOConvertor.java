package com.weitian.convert;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.weitian.dto.OrderDTO;
import com.weitian.entity.OrderDetail;
import com.weitian.enums.ResultEnum;
import com.weitian.exception.SellException;
import com.weitian.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-11-08.
 */
@Slf4j
public class OrderForm2OrderDTOConvertor {
    public static OrderDTO convert(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName( orderForm.getName() );
        orderDTO.setBuyerAddress( orderForm.getAddress() );
        orderDTO.setBuyerOpenId( orderForm.getOpenid() );
        orderDTO.setBuyerAddress( orderForm.getAddress() );
        orderDTO.setBuyerPhone( orderForm.getPhone() );
        Gson gson = new Gson();

        List<OrderDetail> detailList = null;
        try {
            detailList = gson.fromJson( orderForm.getItems(),new TypeToken<List<OrderDetail>>() {}.getType());
        } catch (JsonSyntaxException e) {
            log.error( "【 数据转换失败】,String->List:{}",orderForm.getItems() );
            new SellException( ResultEnum.PARAM_ERROR );
        }

        orderDTO.setOrderDetailList( detailList );
        return orderDTO;
    }
}
