package com.weitian.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Administrator on 2018-11-08.
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名不得为空")
    private String name;
    @NotEmpty(message = "电话不得为空")
    private String phone;
    @NotEmpty(message = "地址不得为空")
    private String address;
    @NotEmpty(message = "openid不得为空")
    private String openid;
    @NotEmpty(message = "购物车不得为空")
    private String items;
}
