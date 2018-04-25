package com.fjy.spring.interceptor;

import com.fjy.spring.constant.GlobalConstant;
import com.fjy.spring.domain.TbAdmin;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {
    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        TbUser user = (TbUser)request.getSession().getAttribute(GlobalConstant.USER_SESSION_KEY);
        //log.info(user.getColuserid()+"");
        //解决service为null无法注入问题
        if (adminService == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            adminService = (AdminService) factory.getBean("adminService");
        }
        Optional<TbAdmin> admin = adminService.findAdminById(user.getColuserid());

        if (!admin.isPresent()){
                response.sendRedirect("/cms/home");
                return false;
        }else {
            return true;
        }
    }

}
