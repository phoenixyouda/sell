package com.weitian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Administrator on 2018-11-08.
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {
    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    CART_EMPTY(18,"购物车为空"),
    ;

    private Integer code;
    private String message;
}
