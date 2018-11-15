package com.weitian.utils;

import com.weitian.vo.ResultVO;

/**
 * Created by Administrator on 2018-11-07.
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg( "成功" );
        resultVO.setData( object );
        return resultVO;
    }
    public static ResultVO success(){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode( 0 );
        resultVO.setMsg( "成功" );
        return resultVO;
    }
    public static ResultVO success(Integer code,String msg,Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg( msg );
        resultVO.setData( object );
        return resultVO;
    }
}
