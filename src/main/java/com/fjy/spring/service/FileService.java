package com.fjy.spring.service;

import com.fjy.spring.domain.TbFile;
import com.fjy.spring.domain.VUserfile;
import com.fjy.spring.repository.TbFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private TbFileRepository tbFileRepository;

    public boolean addFile(TbFile tbFile) {
        TbFile file = tbFileRepository.save(tbFile);
        if (file != null) {
            return true;
        }
        return false;
    }

    public List<TbFile> findFile(TbFile tbFile){
        List<TbFile> files =  tbFileRepository.findByColfilename(tbFile.getColfilename());
        if (files!=null) {
            return files;
        }
        return null;
    }

    public List<TbFile> findAllFile(){
        return tbFileRepository.findAll();
    }

    public TbFile findFileById(TbFile tbFile)
    {
        return tbFileRepository.findById(tbFile.getColfileid()).get();
    }

    public void deleteFile(TbFile file){
        tbFileRepository.delete(file);
    }

    public void deleteFileById(TbFile file){
        tbFileRepository.deleteById(file.getColfileid());
    }

    public List<TbFile> findByWorkFolderAndCourseName(String workFolder,String courseName){
        return tbFileRepository.findByWorkFolderAndCourseName(workFolder,courseName);
    }

    public List<TbFile> findByColuserid(Integer id){
        return tbFileRepository.findByColuserid(id);
    }

    public TbFile findByFilepath(String filePath){
        return tbFileRepository.findByColfilepath(filePath);
    }

}
