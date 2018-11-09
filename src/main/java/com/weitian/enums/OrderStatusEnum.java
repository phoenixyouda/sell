package com.weitian.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Created by Administrator on 2018-11-08.
 */

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    NEW(0,"新订单"),
    FINISH(1,"已完结"),
    CANCEL(2,"已取消"),
    ;
    private Integer code;
    private String message;

}
