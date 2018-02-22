package com.fjy.spring.controller;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.UserService;
import com.fjy.spring.untils.CodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

@Controller
public class UpdateController {
    @Autowired
    private UserService userService;

    @Autowired
    private ServerProperties serverProperties;

    @Resource
    HttpServletRequest request;

    @PostMapping(value = "/home/userUpdate")
    public String doUserUpdate(TbUser tbUser)throws Exception{
        if (tbUser.getColuserid()==null){
            throw new UserException(ResultEnum.ID_NULLPOINT);
        }
        if (tbUser.getColpassword()!=null&&tbUser.getColpassword()!=""){
            //加密用户密码
            tbUser.setColpassword(new BigInteger(CodingUtil.encryptSHA(tbUser.getColpassword().getBytes())).toString(32));
        }
        if (userService.doRegisterService(tbUser)){
            System.out.println(tbUser.getColname()+" 信息更新成功");
            return "redirect:" + request.getScheme() + "://" + request.getServerName() + ":"
                    + serverProperties.getPortNum() + request.getContextPath() + "/home/user";
            // return "login";
        }
        throw new UserException(ResultEnum.UNKOWN_ERROR);
    }
}
