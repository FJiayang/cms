package com.fjy.spring.repository;


import com.fjy.spring.domain.VUserfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VUserfileRepository extends JpaRepository<VUserfile,Integer> {

    public List<VUserfile> findByWorkFolderAndCourseName(String workFolder,String courseName);
}
