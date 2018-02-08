package com.fjy.spring.service;

import com.fjy.spring.domain.TbFeedBack;
import com.fjy.spring.domain.VFeedBack;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.repository.TbFeedBackRepository;
import com.fjy.spring.repository.VFeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackService {
    @Autowired
    TbFeedBackRepository feedBackRepository;

    @Autowired
    VFeedBackRepository vFeedBackRepository;

    public boolean addContent(TbFeedBack feedBack){
        TbFeedBack feed=feedBackRepository.save(feedBack);
        if (feed!=null){
            return true;
        }
        new UserException(ResultEnum.ADD_ERROR);
        return false;
    }

    public List<VFeedBack> findAllVFeedback(){
        return vFeedBackRepository.findAll();
    }
}
