package com.fjy.spring.service;

import com.fjy.spring.domain.TbVersion;
import com.fjy.spring.repository.TbVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {

    @Autowired
    private TbVersionRepository versionRepository;

    public TbVersion addOneVersion(TbVersion tbVersion){
        return versionRepository.save(tbVersion);
    }

    public List<TbVersion> addAllVersion(List<TbVersion> versions){
        return versionRepository.saveAll(versions);
    }

    public List<TbVersion> findAll(){
        return versionRepository.findAll();
    }
}
