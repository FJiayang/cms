package com.fjy.spring.interceptor;

import com.fjy.spring.constant.GlobalConstant;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute(GlobalConstant.USER_SESSION_KEY);
        if (user==null){
            response.sendRedirect("/cms/index");
            return false;
        }
        return true;
    }
}
