package com.fjy.spring.controller;

import com.fjy.spring.domain.VWorkDetail;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.service.WorkDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private WorkDetailService workDetailService;

    @GetMapping("/home/findAllHomework")
    public List<VWorkDetail> findAllHomework(){
        List<VWorkDetail> homeworks = workDetailService.findAll();
        if (homeworks!=null){
            return homeworks;
        }
        new UserException(ResultEnum.EMPTY_DATA);
        return null;
    }
}
