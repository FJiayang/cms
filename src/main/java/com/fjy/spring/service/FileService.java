package com.fjy.spring.service;

import com.fjy.spring.domain.TbFile;
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
        if (file != null)
            return true;
        return false;
    }

    public TbFile findFile(TbFile tbFile){
        TbFile file = (TbFile) tbFileRepository.findByColfilename(tbFile.getColfilename()).get();
        if (file!=null)
            return file;
        return null;
    }

    public List<TbFile> findAllFile(){
        return tbFileRepository.findAll();
    }

    public TbFile findFileById(TbFile tbFile){
        return tbFileRepository.findById(tbFile.getColfileid()).get();
    }



}
