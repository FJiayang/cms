package com.fjy.spring.controller;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.UserService;
import com.fjy.spring.untils.CodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private ServerProperties serverProperties;

    @Resource
    HttpServletRequest request;

    @PostMapping(value = "/register/doregister")
    public String doRegister(@Valid TbUser tbUser, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()){
            ResultEnum resultEnum = ResultEnum.WRONG_FORM;
            resultEnum.setData(bindingResult.getFieldError().getDefaultMessage());
            throw new UserException(resultEnum);
        }
        //加密用户密码
        tbUser.setColpassword(new BigInteger(CodingUtil.encryptSHA(tbUser.getColpassword().getBytes())).toString());
        if (userService.doRegisterService(tbUser)){
            return "redirect:" + request.getScheme() + "://" + request.getServerName() + ":"
                    + serverProperties.getPortNum() + request.getContextPath() + "/index";
            // return "login";
        }
        throw new UserException(ResultEnum.UNKOWN_ERROR);
    }

}
