package com.fjy.spring.service;

import com.fjy.spring.domain.TbStudent;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.repository.TbStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private TbStudentRepository tbStudentRepository;

}
