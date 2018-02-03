package com.fjy.spring.controller;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register/doregister")
    public String doRegister(@Valid TbUser tbUser, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()){
            ResultEnum resultEnum = ResultEnum.WRONG_FORM;
            resultEnum.setData(bindingResult.getFieldError().getDefaultMessage());
            throw new UserException(resultEnum);
        }
        if (userService.doRegisterService(tbUser)){
            return "login";
        }
        throw new UserException(ResultEnum.UNKOWN_ERROR);
    }

}
