package com.fjy.spring.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void doRegister()throws Exception {
        //测试正常注册,abc每次调试必须改，因为唯一约束
        mvc.perform(MockMvcRequestBuilders.post("/register/doregister")
                .param("colname", "abc")
                .param("colpassword", "123456")
                .param("colemail","test@gmail.com")
                .param("colstudentno","0003")
                .param("colrealname","TestRegister"))
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"code\": 0,\n" +
                        "    \"message\": \"请求成功\",\n" +
                        "    \"data\": null\n" +
                        "}"));
    }
}