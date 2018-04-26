package com.fjy.spring.service;

import com.fjy.spring.domain.TbAdmin;
import com.fjy.spring.domain.VAdmin;
import com.fjy.spring.repository.TbAdminRepository;
import com.fjy.spring.repository.VAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private TbAdminRepository adminRepository;

    @Autowired
    private VAdminRepository vAdminRepository;

    public Optional<TbAdmin>  findAdminById(Integer id){
        return adminRepository.findByUserid(id);
    }

    public Optional<VAdmin> findAdminByUserId(Integer id){
        return vAdminRepository.findById(id);
    }
}
