package com.weitian.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.weitian.controller.SellerOrderController;
import com.weitian.utils.ResultVOUtil;
import com.weitian.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018-11-14.
 */
@ControllerAdvice
public class SellExceptionHandle {
    @ExceptionHandler(SellException.class)
    @ResponseBody
    public ResultVO executeException(SellException e){
        return ResultVOUtil.success(e.getCode(),e.getMessage(),null);

    }
}
