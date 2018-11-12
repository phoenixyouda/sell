package com.weitian.utils;

import com.weitian.enums.CodeEnum;

/**
 * Created by Administrator on 2018-11-12.
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code,Class<T> enumCls){
        for(T t:enumCls.getEnumConstants()){
            if(code.equals( t.getCode() )){
                return t;
            }
        }
        return null;
    }
}
