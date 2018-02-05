package com.fjy.spring.controller;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.UserService;
import com.fjy.spring.untils.CodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

@Controller
public class LoginController {
    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private UserService userService;

    @Resource
    HttpServletRequest request;

    @PostMapping("/login/dologin")
    public String doLogin(TbUser tbUser)throws Exception{
        //加密用户密码
        tbUser.setColpassword(new BigInteger(CodingUtil.encryptSHA(tbUser.getColpassword().getBytes())).toString(32));
        TbUser user = userService.doLoginService(tbUser.getColname(),tbUser.getColpassword());
        if (user!=null){
            request.getSession().setAttribute(USER_SESSION_KEY,user);
            return  "redirect:" + request.getScheme() + "://" + request.getServerName() + ":"
                    + serverProperties.getPortNum() + request.getContextPath() + "/home";
        }
        return "login";
    }
}
