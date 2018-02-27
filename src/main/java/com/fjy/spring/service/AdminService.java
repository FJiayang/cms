package com.fjy.spring.service;

import com.fjy.spring.domain.TbAdmin;
import com.fjy.spring.repository.TbAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private TbAdminRepository adminRepository;

    public Optional<TbAdmin>  findAdminById(Integer id){
        return adminRepository.findByUserid(id);
    }
}
