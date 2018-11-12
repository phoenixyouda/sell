package com.weitian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Administrator on 2018-11-08.
 */
@AllArgsConstructor
@Getter
public enum PayStatusEnum implements CodeEnum{
    wait(0,"等待支付"),
    finish(1,"支付完成");
    private Integer code;
    private String message;
}
