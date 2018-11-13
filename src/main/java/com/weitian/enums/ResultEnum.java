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
    PRODUCT_IS_EMPTY(9,"暂无商品"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    PRODUCT_STATUS_ERROR(12,"商品状态不正确"),
    CART_EMPTY(18,"购物车为空"),
    WECHAT_MP_ERROR(19,"微信授权失败"),
    ORDER_IS_EMPTY(20,"暂无订单"),
    ORDERDETAIL_NOT_EXIST(21,"订单详情不存在"),
    ORDER_NOT_EXIST(22,"订单不存在"),
    ;

    private Integer code;
    private String message;
}
