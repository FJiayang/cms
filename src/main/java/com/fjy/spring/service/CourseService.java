package com.fjy.spring.service;

import com.fjy.spring.domain.VCourse;
import com.fjy.spring.repository.VCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private VCourseRepository vcourseRepository;

    public List<VCourse> findAllVCourse(){
        return vcourseRepository.findAll();
    }
}
