package com.weitian.exception;

import com.weitian.enums.ResultEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by Administrator on 2018-11-08.
 */
@Data
public class SellException extends RuntimeException {
    private Integer code;
    private String message;
    public SellException(ResultEnum resultEnum){
       this.message=resultEnum.getMessage();
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code,String message){
        this.message=message;
        this.code=code;
    }

}
