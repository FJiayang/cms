package com.fjy.spring.service;

import com.fjy.spring.domain.TbFeedBack;
import com.fjy.spring.enums.ResultEnum;
import com.fjy.spring.exception.UserException;
import com.fjy.spring.repository.TbFeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedBackService {
    @Autowired
    TbFeedBackRepository feedBackRepository;

    public boolean addContent(TbFeedBack feedBack){
        TbFeedBack feed=feedBackRepository.save(feedBack);
        if (feed!=null){
            return true;
        }
        new UserException(ResultEnum.ADD_ERROR);
        return false;
    }
}
