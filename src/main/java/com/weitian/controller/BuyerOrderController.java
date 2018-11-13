package com.weitian.controller;

import com.weitian.convert.OrderForm2OrderDTOConverter;
import com.weitian.dto.OrderDTO;
import com.weitian.enums.ResultEnum;
import com.weitian.exception.SellException;
import com.weitian.form.OrderForm;
import com.weitian.service.OrderService;
import com.weitian.utils.ResultVOUtil;
import com.weitian.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018-11-08.
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error( "【创建订单失败】,{},OrderForm:{},{}", ResultEnum.PARAM_ERROR.getMessage(),orderForm,bindingResult.getFieldError().getDefaultMessage() );
            new SellException( ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO= OrderForm2OrderDTOConverter.convert( orderForm );

        if(CollectionUtils.isEmpty( orderDTO.getOrderDetailList() )){
            log.error( "【创建订单失败】,购物车不得为空,openId:{}",orderDTO.getBuyerOpenId() );
            new SellException( ResultEnum.CART_EMPTY );
        }


        orderDTO= orderService.create( orderDTO );
        Map<String, String> map = new HashMap<String, String>();
        map.put("openid",orderDTO.getOrderId());
        return ResultVOUtil.success( map );
    }
}
