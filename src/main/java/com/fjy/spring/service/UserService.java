package com.fjy.spring.service;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.repository.TbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private TbUserRepository tbUserRepository;


    public boolean doLoginService(String name,String password){
        TbUser user = (TbUser)tbUserRepository.findByColname(name).get();
        if (password.equals(user.getColpassword())){
            return true;
        }
        return false;
    }
}
