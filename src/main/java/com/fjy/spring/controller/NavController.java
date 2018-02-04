package com.fjy.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
    @GetMapping(value = {"index",""})
    public String toLoginPage(){
        return "login";
    }

    @GetMapping(value = {"testthymeleaf"})
    public String toTestPage(){
        return "/dist/thymeleafTest";
    }
}
