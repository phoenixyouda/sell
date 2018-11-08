package com.weitian.controller;

import com.weitian.entity.ProductCategory;
import com.weitian.entity.ProductInfo;
import com.weitian.service.ProductCategoryService;
import com.weitian.service.ProductInfoService;
import com.weitian.utils.ResultVOUtil;
import com.weitian.vo.ProductVO;
import com.weitian.vo.ProductInfoVO;
import com.weitian.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018-11-07.
 */
@RestController
@RequestMapping("buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @GetMapping("list")
    public ResultVO list(){
        //查询商品
        List<ProductInfo> productInfoList=productInfoService.findUpAll();
        //查询类目
        List<Integer> categoryTypeList= productInfoList.stream().map( e->e.getCategoryType() ).collect( Collectors.toList() );

        List<ProductCategory> productCategoryList=productCategoryService.findByCategoryTypeIn( categoryTypeList );
        //封装数据
        List<ProductVO> productVOList =new ArrayList<>(  );

        for(ProductCategory productCategory:productCategoryList){
            List<ProductInfoVO> productInfoVOList =new ArrayList<>(  );
            ProductVO productVO =new ProductVO();
            productVO.setCategoryType( productCategory.getCategoryType() );
            productVO.setCategoryName( productCategory.getCategoryName() );
            for(ProductInfo productInfo:productInfoList){
                if(productCategory.getCategoryType().equals( productInfo.getCategoryType() )){
                    ProductInfoVO productInfoVO =new ProductInfoVO();
                    BeanUtils.copyProperties( productInfo, productInfoVO );
                    productInfoVOList.add( productInfoVO );

                }
            }
            productVO.setFoods( productInfoVOList );
            productVOList.add( productVO );
        }


        return ResultVOUtil.success( productVOList );
    }
}
