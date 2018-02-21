package com.fjy.spring.repository;

import com.fjy.spring.domain.TbFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TbFileRepository extends JpaRepository<TbFile,Integer>{
    public List<TbFile> findByColfilename(String name);

    public List<TbFile> findByWorkFolderAndCourseName(String workFolder,String courseName);
}
