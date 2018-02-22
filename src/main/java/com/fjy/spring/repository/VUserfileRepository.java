package com.fjy.spring.repository;


import com.fjy.spring.domain.TbStudentlist;
import com.fjy.spring.domain.VUserfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VUserfileRepository extends JpaRepository<VUserfile,Integer> {

    public List<VUserfile> findByWorkFolderAndCourseName(String workFolder,String courseName);

    @Query(value = "SELECT l FROM TbStudentlist l WHERE l.colstudentno NOT IN ( SELECT colstudentno FROM VUserfile f WHERE f.workFolder = ?1 AND f.courseName = ?2 )")
    public List<TbStudentlist> findStudentNoByWorkFolderAndCourseName(String workFolder, String courseName);
}
