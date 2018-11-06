package com.weitian.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018-11-06.
 */
@AllArgsConstructor
@Getter
public enum ProductInfoStatus {
    UP(1,"上架"), DOWN(2,"下架");
    private Integer code;
    private String message;

}
