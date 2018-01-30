package com.fjy.spring.controller;

import com.fjy.spring.domain.TbUser;
import com.fjy.spring.repository.TbUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TbUserRepository tbUserRepository;

    @GetMapping("/test")
    public String TestHello(){
        return "Hello Spring";
    }

    @GetMapping("/test/name/{name}")
    public String TestDataBase(@PathVariable("name") String name){
        //TbUser user = (TbUser)tbUserRepository.findById(id).get();
        TbUser user = (TbUser)tbUserRepository.findByColname(name).get();
        return user.getColpassword();
    }

    @GetMapping("/test/id/{id}")
    public String TestDataBaseId(@PathVariable("id") Integer id){
        TbUser user = (TbUser)tbUserRepository.findById(id).get();
        return user.toString();
    }
}