package com.fjy.spring.untils;

import com.fjy.spring.properties.ServerProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class RedirectUtil {
    @Autowired
    private ServerProperties serverProperties;

    public String Redirect(HttpServletRequest request,String url){
        return "redirect:" + request.getScheme() + "://" + request.getServerName() + ":"
                + serverProperties.getPortNum() + request.getContextPath() + "/"+url;
    }
}
