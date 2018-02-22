package com.fjy.spring.service;

import com.fjy.spring.domain.TbStudentlist;
import com.fjy.spring.domain.VUserfile;
import com.fjy.spring.repository.VUserfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VUserfileService {
    @Autowired
    private VUserfileRepository vUserfileRepository;

    public List<VUserfile> findByWorkFolderAndCourseName(String workFolder, String courseName) {
        return vUserfileRepository.findByWorkFolderAndCourseName(workFolder, courseName);
    }

    public List<TbStudentlist> findStudentNoByWorkFolderAndCourseName(String workFolder, String courseName){
        return vUserfileRepository.findStudentNoByWorkFolderAndCourseName(workFolder,courseName);
    }
}