package com.fjy.spring.service;

import com.fjy.spring.domain.VWorkDetail;
import com.fjy.spring.repository.WorkDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkDetailService {
    @Autowired
    private WorkDetailRepository workDetailRepository;

    public List<VWorkDetail> findAll(){
        return workDetailRepository.findAll();
    }
}
