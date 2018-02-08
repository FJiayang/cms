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

    @GetMapping(value = {"axiosTest"})
    public String toTestAxiosPage(){
        return "/dist/axiosTest";
    }

    @GetMapping(value = {"/home"})
    public String toHomePage(){
        return "/home/home";
    }

    @GetMapping(value = {"/home/feedback"})
    public String toFeedbackPage(){
        return "/home/feedback";
    }

    @GetMapping(value = {"/home/about"})
    public String toAboutPage(){
        return "/home/about";
    }

    @GetMapping(value = {"/admin"})
    public String toAdminPage(){
        return "/home/admin";
    }

    @GetMapping(value = {"/home/user"})
    public String toUserPage(){
        return "/home/user";
    }
}
