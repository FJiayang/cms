package com.fjy.spring.repository;

import com.fjy.spring.domain.TbFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbFileRepository extends JpaRepository<TbFile,Integer>{
    public List<TbFile> findByColfilename(String name);

    public List<TbFile> findByWorkFolderAndCourseName(String workFolder,String courseName);

    public List<TbFile> findByColuserid(Integer id);

    public TbFile findByColfilepath(String filePath);
}
