package com.fjy.spring.controller;

import com.fjy.spring.domain.TbLog;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.domain.VUserinfo;
import com.fjy.spring.enums.RegisteredEnum;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.LogService;
import com.fjy.spring.service.StudentService;
import com.fjy.spring.service.UserService;
import com.fjy.spring.untils.CodingUtil;
import com.fjy.spring.untils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

@Controller
@Slf4j
public class UpdateController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LogService logService;

    @Resource
    HttpServletRequest request;

    @PostMapping(value = "/home/userUpdate")
    @ResponseBody
    public boolean doUserUpdate(TbUser tbUser)throws Exception{
        if (tbUser.getColuserid()==null){
            throw new UserException(ResultEnum.ID_NULLPOINT);
        }
        if (tbUser.getColpassword()!=null){
            userService.updateColpasswordByColname(tbUser.getColpassword(),tbUser.getColname());
        }
        //注销原本的注册标记
        VUserinfo tempUser = userService.findUserInfo(tbUser.getColuserid());
        studentService.UpdateStudentListRegistered(tempUser.getColrealname(),tempUser.getColstudentno(),
                RegisteredEnum.UNREGISTERED.getCode());
        if (userService.doRegisterService(tbUser)){
            studentService.UpdateStudentListRegistered(tbUser.getColrealname(),tbUser.getColstudentno(),
                    RegisteredEnum.REGISTERED.getCode());
            log.info(tbUser.getColname()+" 信息更新成功");
            //写入数据库日志
            TbUser user =(TbUser)request.getSession().getAttribute(USER_SESSION_KEY);
            TbLog tbLog = LogUtil.addLog(user,tbUser.getColname()+" 信息更新成功",request);
            logService.addLogRec(tbLog);

            return true;
            /*return "redirect:" + request.getScheme() + "://" + request.getServerName() + ":"
                    + serverProperties.getPortNum() + request.getContextPath() + "/home/user";*/
            // return "login";
        }
        //写入数据库日志
        TbUser user =(TbUser)request.getSession().getAttribute(USER_SESSION_KEY);
        TbLog tbLog = LogUtil.addLog(user,tbUser.getColname()+" 信息更新失败",request);
        logService.addLogRec(tbLog);
        log.error(tbUser.getColname()+" 信息更新失败");
        return false;
    }
}
