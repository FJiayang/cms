package com.fjy.spring.interceptor;

import com.fjy.spring.constant.GlobalConstant;
import com.fjy.spring.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TbUser user = (TbUser)request.getSession().getAttribute(GlobalConstant.USER_SESSION_KEY);
        if (!user.getColname().equals("root")){
            response.sendRedirect("/cms/home");
            return false;
        }
        return true;
    }

}
