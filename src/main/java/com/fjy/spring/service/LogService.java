package com.fjy.spring.service;

import com.fjy.spring.domain.TbLog;
import com.fjy.spring.domain.VLog;
import com.fjy.spring.repository.TbLogRepository;
import com.fjy.spring.repository.VLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private TbLogRepository tbLogRepository;

    @Autowired
    private VLogRepository vLogRepository;

    public void addLogRec(TbLog tbLog){
        tbLogRepository.save(tbLog);
    }

    public List<VLog> findvlog(){
        return vLogRepository.findAll();
    }
}
