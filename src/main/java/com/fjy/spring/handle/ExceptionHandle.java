package com.fjy.spring.handle;

import com.fjy.spring.domain.Result;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.untils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof UserException){
            UserException userException = (UserException)e;
            return ResultUtil.error(userException.getCode(),userException.getMessage());
        }else{
            logger.error("系统异常",e);
            return ResultUtil.error(ResultEnum.UNKOWN_ERROR.getCode(),ResultEnum.UNKOWN_ERROR.getMsg());
        }
    }
}
