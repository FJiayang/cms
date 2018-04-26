package com.fjy.spring.controller;

import com.fjy.spring.domain.TbLog;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.service.LogService;
import com.fjy.spring.untils.GetIPAddrUtil;
import com.fjy.spring.untils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

@Controller
@SessionAttributes(USER_SESSION_KEY)
public class NavController {
    @Resource
    HttpServletRequest request;

    @Autowired
    private LogService logService;

    @GetMapping(value = {"index",""})
    public String toLoginPage(){
        //获取访问头信息
        addIndexLog(request.getHeader("user-agent"));
        return "login";
    }

    @GetMapping(value = {"testthymeleaf"})
    public String toTestPage(){
        addVisitLog();
        return "/dist/thymeleafTest";
    }

    @GetMapping(value = {"axiosTest"})
    public String toTestAxiosPage(){
        addVisitLog();
        return "/dist/axiosTest";
    }

    @GetMapping(value = {"/home"})
    public String toHomePage(){
        addVisitLog();
        return "home/home";
    }

    @GetMapping(value = {"/logout"})
    public String toLogOut(SessionStatus status){
        //request.getSession().getAttribute(USER_SESSION_KEY).invalidate();
        addVisitLog();
        status.setComplete();
        return "login";
    }

    @GetMapping(value = {"/home/feedback"})
    public String toFeedbackPage(){
        addVisitLog();
        return "home/feedback";
    }

    @GetMapping(value = {"/home/about"})
    public String toAboutPage(){
        addVisitLog();
        return "home/about";
    }

    @GetMapping(value = {"/home/admin"})
    public String toAdminPage(){
        addVisitLog();
        return "home/admin";
    }

    @GetMapping(value = {"/home/admin/managecourse"})
    public String toManageCoursePage(){
        addVisitLog();
        return "home/managecourse";
    }

    @GetMapping(value = {"/home/admin/manageuser"})
    public String toManageUserPage(){
        addVisitLog();
        return "home/manageuser";
    }

    @GetMapping(value = {"/home/admin/homework"})
    public String toHomeworkPage(){
        addVisitLog();
        return "home/homework";
    }

    @GetMapping(value = {"/home/user"})
    public String toUserPage(){
        addVisitLog();
        return "home/user";
    }

    @GetMapping(value = {"/error"})
    public String toErrorPage(){
        addVisitLog();
        return "error";
    }

    /**
     * 首页的访问日志记录
     * @param content
     */
    private void addIndexLog(String content){
        TbUser user = new TbUser();
        user.setColname("访客");
        TbLog log = LogUtil.addLog(user,content,request);
        logService.addLogRec(log);
    }

    /**
     * 登陆后的访问日志记录
     */
    private void addVisitLog(){
        TbUser user =(TbUser)request.getSession().getAttribute(USER_SESSION_KEY);
        TbLog log = LogUtil.addLog(user,request.getHeader("user-agent"),request);
        logService.addLogRec(log);
    }
}
