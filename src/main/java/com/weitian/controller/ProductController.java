package com.weitian.controller;

import com.weitian.dto.ProductDTO;
import com.weitian.entity.ProductInfo;
import com.weitian.exception.SellException;
import com.weitian.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018-11-13.
 */
@Controller
@RequestMapping("/seller/product")
public class ProductController {
    @Autowired
    private ProductInfoService productService;


    @GetMapping("/mytest")
    @ResponseBody
    public String mytest(String productId){
        if(productId.equals( "1234" )){
            productId="";
        }
        productService.up( productId );
        return "1234567";
    }

    /**
     * 商品上架
     * @param productId
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/up")
    public String up(@RequestParam("productId")String productId,Model model,HttpServletRequest request){
        try {
            productService.up( productId );
        } catch (SellException e) {
            model.addAttribute( "message",e.getMessage() );
            model.addAttribute( "url", request.getContextPath()+"/seller/product/list");
            return "/common/error";
        }
        model.addAttribute( "message","商品上架成功" );
        model.addAttribute( "url",request.getContextPath()+"/seller//product/list" );
        return "/common/success";
    }

    /**
     * 商品下架
     * @param productId
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/down")
    public String down(@RequestParam("productId")String productId,Model model,HttpServletRequest request){
        try {
            productService.down( productId );
        } catch (SellException e) {
            model.addAttribute( "message",e.getMessage() );
            model.addAttribute( "url", request.getContextPath()+"/seller/product/list");
            return "/common/error";
        }

        model.addAttribute( "message","商品下架成功" );
        model.addAttribute( "url",request.getContextPath()+"/seller/product/list" );
        return "/common/success";
    }

    /**
     * 商品列表
     * @param page
     * @param size
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/list")
    public String list(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value="size",defaultValue = "10") Integer size, Model model, HttpServletRequest request){
        PageRequest pageRequest=PageRequest.of( page-1,size );
        try {
            Page<ProductDTO> productPage=productService.findAll( pageRequest );
            model.addAttribute( "productPage",productPage );
            model.addAttribute( "page",page );
            model.addAttribute( "size",size );
        } catch (SellException e) {
            model.addAttribute( "message",e.getMessage() );
            // TODO: 2018-11-13
            model.addAttribute( "url","" );

            return "common/error";
        }
        return "/product/list";
    }
}
