package com.fjy.spring.service;

import com.fjy.spring.domain.Homework;
import com.fjy.spring.domain.VHomework;
import com.fjy.spring.repository.HomeworkRepository;
import com.fjy.spring.repository.VHomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Homework findById(Integer id){
        Optional<Homework> homework = homeworkRepository.findById(id);
        if (homework.isPresent()){
            return (Homework)homework.get();
        }
        return null;
    }
}
