package com.fjy.spring.service;

import com.fjy.spring.domain.TbStudent;
import com.fjy.spring.domain.TbStudentlist;
import com.fjy.spring.domain.TbUser;
import com.fjy.spring.enums.RegisteredEnum;
import com.fjy.spring.repository.TbStudentListRepository;
import com.fjy.spring.repository.TbStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private TbStudentRepository tbStudentRepository;

    @Autowired
    private TbStudentListRepository tbStudentListRepository;

    public TbStudentlist findStudentByNo(String studentno){
        return tbStudentListRepository.findByColstudentno(studentno);
    }

    public TbStudentlist findByColstudentnoAndColrealname(String studentno,String realname){
        return tbStudentListRepository.findByColstudentnoAndColrealname(studentno,realname);
    }

    public TbStudentlist updateStudentListRegistered(String realname,String studentno,Integer code){
        TbStudentlist studentlist = findByColstudentnoAndColrealname(studentno,realname);
        studentlist.setRegistered(code);
        return tbStudentListRepository.save(studentlist);
    }


}
