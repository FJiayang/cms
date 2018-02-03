package com.fjy.spring.controller;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"index",""})
    public String toLoginPage(){
        return "login";
    }

    @PostMapping("/login/dologin")
    public String doLogin(TbUser tbUser)throws Exception{
        if (userService.doLoginService(tbUser.getColname(),tbUser.getColpassword())){
            return "/home/home";
        }
        return "login";
    }
}
