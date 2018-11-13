package com.weitian.controller;

import com.weitian.dto.OrderDTO;
import com.weitian.exception.SellException;
import com.weitian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018-11-12.
 */
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {
    @Autowired
    private OrderService orderService;

    /**
     *完结订单
     * @param orderId
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/finish")
    public String finish(@RequestParam("orderId") String orderId, Model model, HttpServletRequest request){
        try {
            orderService.finish( orderId );
        } catch (SellException e) {
            model.addAttribute( "message",e.getMessage() );
            model.addAttribute( "url", request.getContextPath()+"/seller/order/list");
            return "common/error";
        }
        model.addAttribute( "message","订单已完结" );
        model.addAttribute( "url", request.getContextPath()+"/seller/order/list");
        return "common/success";
    }
    /**取消订单
     *
     * */
    @GetMapping("cancel")
    public String cancel(@RequestParam("orderId") String orderId, Model model, HttpServletRequest request){
        try {
            orderService.cancel( orderId );
        } catch (SellException e) {
            model.addAttribute( "message",e.getMessage() );
            model.addAttribute( "url", request.getContextPath()+"/seller/order/list");
            return "common/error";
        }
        model.addAttribute( "message","订单已取消" );
        model.addAttribute( "url", request.getContextPath()+"/seller/order/list");
        return "common/success";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("orderId") String orderId,Model model,HttpServletRequest request){

        OrderDTO orderDTO= null;
        try {
            orderDTO = orderService.findByOrderId( orderId );
        } catch (SellException e) {
            model.addAttribute( "message",e.getMessage() );
            model.addAttribute( "url", request.getContextPath()+"/seller/order/list");
            return "common/error";
        }
        model.addAttribute("orderDTO",orderDTO);
        return "order/detail";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value="page",defaultValue = "1") Integer page,@RequestParam(value="size",defaultValue = "10") Integer size,Model model,HttpServletRequest request){
        PageRequest pageRequest=PageRequest.of( page-1,size );
        Page<OrderDTO> orderDTOPage= null;

        try {
            orderDTOPage = orderService.findAll( pageRequest );
        } catch (SellException e) {
            model.addAttribute( "message",e.getMessage() );
            model.addAttribute( "url", request.getContextPath()+"/seller/order/list");

            return "common/error";
        }
        model.addAttribute( "orderDTOPage",orderDTOPage );
        model.addAttribute( "currPage",page );
        model.addAttribute( "size",size );
        return "order/list";
    }
}
