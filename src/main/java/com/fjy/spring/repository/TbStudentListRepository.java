package com.fjy.spring.repository;

import com.fjy.spring.domain.TbStudentlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbStudentListRepository extends JpaRepository<TbStudentlist,Integer> {

    public TbStudentlist findByColstudentno(String colstudentno);

    public TbStudentlist findByColstudentnoAndColrealname(String colstudentno,String colrealname);
}
