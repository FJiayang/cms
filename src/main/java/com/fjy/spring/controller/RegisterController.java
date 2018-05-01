package com.fjy.spring.controller;

import com.fjy.spring.domain.TbStudentlist;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.enums.RegisteredEnum;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.properties.ServerProperties;
import com.fjy.spring.service.StudentService;
import com.fjy.spring.service.UserService;
import com.fjy.spring.untils.CodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ServerProperties serverProperties;

    @Resource
    HttpServletRequest request;

    @PostMapping(value = "/register/doregister")
    @ResponseBody
    public boolean doRegister(@Valid TbUser tbUser, BindingResult bindingResult)throws Exception{
        if (bindingResult.hasErrors()){
            ResultEnum resultEnum = ResultEnum.WRONG_FORM;
            resultEnum.setData(bindingResult.getFieldError().getDefaultMessage());
            throw new UserException(resultEnum);
        }
        //加密用户密码
        tbUser.setColpassword(new BigInteger(CodingUtil.encryptSHA(tbUser.getColpassword().getBytes())).toString(32));
        if (userService.doRegisterService(tbUser)){
            //更新用户列表是否注册的标记
            studentService.updateStudentListRegistered(tbUser.getColrealname(),tbUser.getColstudentno(),RegisteredEnum.REGISTERED.getCode());
            return true;
            /*return "redirect:" + request.getScheme() + "://" + request.getServerName() + ":"
                    + serverProperties.getPortNum() + request.getContextPath() + "/index";*/
            // return "login";
        }
        throw new UserException(ResultEnum.UNKOWN_ERROR);
    }

    @GetMapping("/CheckStudentNo")
    @ResponseBody
    public boolean doCheckStudentNo(@RequestParam(value = "studentno") String studentno){
        TbStudentlist studentlist = studentService.findStudentByNo(studentno);
        if (studentlist!=null) {
            return true;
        }
        return false;
    }

    @GetMapping("/CheckStudent")
    @ResponseBody
    public boolean doCheckStudent(@RequestParam(value = "studentno") String studentno,
                                  @RequestParam(value = "realname") String realname){
        TbStudentlist studentlist = studentService.findByColstudentnoAndColrealname(studentno,realname);
        if (studentlist==null||studentlist.getRegistered().equals(RegisteredEnum.REGISTERED.getCode()) ) {
            return false;
        }
        return true;
    }

    /**
     * 查询用户名是否存在
     * @param name
     * @return
     */
    @GetMapping("/CheckUserName")
    @ResponseBody
    public boolean doUserName(@RequestParam(value = "name") String name){
        Optional<TbUser> user = userService.findByColname(name);
        if (user.isPresent()) {
            return false;
        }
        return true;
    }

}
