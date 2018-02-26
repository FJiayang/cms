package com.fjy.spring.service;

import com.fjy.spring.domain.TbNotice;
import com.fjy.spring.repository.TbNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private TbNoticeRepository noticeRepository;

    public List<TbNotice> findAll(){
        return noticeRepository.findAll();
    }

}
