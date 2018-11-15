package com.weitian.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2018-11-07.
 */
@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ResultVO<T> {
    private Integer code;
    private String msg;

    private T data;
}
