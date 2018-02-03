package com.fjy.spring.untils;

import com.fjy.spring.domain.Result;

public class ResultUtil {
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }
    public static Result error(Integer code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        return result;
    }
}
