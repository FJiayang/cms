package com.fjy.spring.controller;

import com.fjy.spring.domain.*;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    @GetMapping("/home/findAllHomework")
    public List<VWorkDetail> findAllHomework(){
        List<VWorkDetail> homeworks = workDetailService.findAll();
        if (homeworks!=null){
            return homeworks;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @GetMapping("/home/findvlog")
    public List<VLog> findlog(){
        List<VLog> vlogs = logService.findvlog();
        if (vlogs!=null){
            return vlogs;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @GetMapping("/home/findvfeedback")
    public List<VFeedBack> findAllVFeedback(){
        List<VFeedBack> feedBacks = feedBackService.findAllVFeedback();
        if (feedBacks!=null){
            return feedBacks;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @GetMapping("/home/findvcourse")
    public List<VCourse> findVCourse(){
        List<VCourse> vCourses = courseService.findAllVCourse();
        if (vCourses!=null){
            return vCourses;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @GetMapping("/home/findalluser")
    public List<TbUser> findAllUser(){
        List<TbUser> users = userService.findAllUser();
        if (users!=null){
            return users;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @GetMapping("/home/findallvhomework")
    public List<VHomework> findAllVHomework(){
        List<VHomework> vHomeworks = homeworkService.findAllVHomework();
        if (vHomeworks!=null){
            return vHomeworks;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }

    @GetMapping("/home/findStudentInCourseFile")
    public  List<VUserfile> findStudentInCourseFile(
            @RequestParam(value = "Folder") String Folder,@RequestParam(value = "CourseName") String CourseName){
        List<VUserfile> files = vUserfileService.findByWorkFolderAndCourseName(Folder,CourseName);
        if (files!=null){
            return files;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }
}
