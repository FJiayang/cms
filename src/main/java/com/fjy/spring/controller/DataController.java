package com.fjy.spring.controller;

import com.fjy.spring.domain.VFeedBack;
import com.fjy.spring.domain.VLog;
import com.fjy.spring.domain.VWorkDetail;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.service.FeedBackService;
import com.fjy.spring.service.LogService;
import com.fjy.spring.service.WorkDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
