package com.fjy.spring.controller;

import com.fjy.spring.domain.*;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.service.*;
import com.fjy.spring.untils.CodingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.List;

import static com.fjy.spring.constant.GlobalConstant.USER_SESSION_KEY;

@RestController
@Slf4j
public class DataController {

    @Autowired
    private WorkDetailService workDetailService;

    @Autowired
    private LogService logService;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private FileService fileService;

    @Autowired
    private VUserfileService vUserfileService;

    @Resource
    private HttpServletRequest httpServletRequest;

    @GetMapping("/home/findAllHomework")
    public List<VWorkDetail> findAllHomework(){
        List<VWorkDetail> homeworks = workDetailService.findAll();
        if (homeworks!=null){
            return homeworks;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findvlog")
    public List<VLog> findlog(){
        List<VLog> vlogs = logService.findvlog();
        if (vlogs!=null){
            return vlogs;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findvfeedback")
    public List<VFeedBack> findAllVFeedback(){
        List<VFeedBack> feedBacks = feedBackService.findAllVFeedback();
        if (feedBacks!=null){
            return feedBacks;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findvcourse")
    public List<VCourse> findVCourse(){
        List<VCourse> vCourses = courseService.findAllVCourse();
        if (vCourses!=null){
            return vCourses;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findalluser")
    public List<TbUser> findAllUser(){
        List<TbUser> users = userService.findAllUser();
        if (users!=null){
            return users;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findallvhomework")
    public List<VHomework> findAllVHomework(){
        List<VHomework> vHomeworks = homeworkService.findAllVHomework();
        if (vHomeworks!=null){
            return vHomeworks;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/findStudentInCourseFile")
    public  List<TbStudentlist> findStudentInCourseFile(
            @RequestParam(value = "Folder") String Folder,@RequestParam(value = "CourseName") String CourseName){
        List<TbStudentlist> files = vUserfileService.findStudentNoByWorkFolderAndCourseName(Folder,CourseName);
        if (files!=null){
            return files;
        }
        throw new UserException(ResultEnum.EMPTY_DATA);
    }

    @GetMapping("/home/userinfo")
    public VUserinfo findUserInfo(){
        TbUser user= (TbUser)httpServletRequest.getSession().getAttribute(USER_SESSION_KEY);
        return userService.findUserInfo(user.getColuserid());
    }

    /**
     * 存储密保问题
     * @param userque
     * @return
     */
    @PostMapping("/home/adduserque")
    public boolean adduserque(TbUserque userque)throws Exception{
        log.info(userque.toString());
        //对密保问题加密存储
        userque.setAnswer(new BigInteger(CodingUtil.encryptSHA(userque.getAnswer().getBytes())).toString(32));
        return userService.addUserQue(userque);
    }
}
