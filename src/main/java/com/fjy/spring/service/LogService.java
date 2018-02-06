package com.fjy.spring.service;

import com.fjy.spring.domain.TbLog;
import com.fjy.spring.repository.TbLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    @Autowired
    private TbLogRepository tbLogRepository;

    public void addLogRec(TbLog tbLog){
        tbLogRepository.save(tbLog);
    }
}
