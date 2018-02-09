package com.fjy.spring.service;

import com.fjy.spring.domain.Homework;
import com.fjy.spring.domain.VHomework;
import com.fjy.spring.domain.VWorkDetail;
import com.fjy.spring.repository.HomeworkRepository;
import com.fjy.spring.repository.VHomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;

    @Autowired
    private VHomeworkRepository vHomeworkRepository;

    public List<Homework> findAll(){
        return homeworkRepository.findAll();
    }

    public List<VHomework> findAllVHomework(){
        return vHomeworkRepository.findAll();
    }
}
