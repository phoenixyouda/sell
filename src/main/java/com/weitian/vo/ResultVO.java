package com.weitian.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018-11-07.
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;
}
