package com.weitian.controller;

import com.weitian.dto.OrderDTO;
import com.weitian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018-11-12.
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value="page",defaultValue = "1") Integer page,@RequestParam(value="size",defaultValue = "10") Integer size){
        PageRequest request=PageRequest.of( page-1,size );
        Page<OrderDTO> orderDTOPage=orderService.findAll( request );
        Map<String,Object> map=new HashMap<String,Object>(  );
        map.put( "orderDTOPage",orderDTOPage );
        map.put( "currPage",page );
        map.put( "size",size );


        ModelAndView mv=new ModelAndView( "order/list",map );
        return mv;
    }
}
