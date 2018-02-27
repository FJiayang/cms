package com.fjy.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class NavController {
    @Resource
    HttpServletRequest request;

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
        return "home/home";
    }

    @GetMapping(value = {"/logout"})
    public String toLogOut(SessionStatus status){
        //request.getSession().getAttribute(USER_SESSION_KEY).invalidate();
        status.setComplete();
        return "login";
    }

    @GetMapping(value = {"/home/feedback"})
    public String toFeedbackPage(){
        return "home/feedback";
    }

    @GetMapping(value = {"/home/about"})
    public String toAboutPage(){
        return "home/about";
    }

    @GetMapping(value = {"/home/admin"})
    public String toAdminPage(){
        return "home/admin";
    }

    @GetMapping(value = {"/home/admin/managecourse"})
    public String toManageCoursePage(){
        return "home/managecourse";
    }

    @GetMapping(value = {"/home/admin/manageuser"})
    public String toManageUserPage(){
        return "home/manageuser";
    }

    @GetMapping(value = {"/home/admin/homework"})
    public String toHomeworkPage(){
        return "home/homework";
    }

    @GetMapping(value = {"/home/user"})
    public String toUserPage(){
        return "home/user";
    }

    @GetMapping(value = {"/error"})
    public String toErrorPage(){
        return "error";
    }
}
