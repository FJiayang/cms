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

import javax.transaction.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void toLoginPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    public void doLogin() throws Exception {
        //测试正常登录
        mvc.perform(MockMvcRequestBuilders.post("/login/dologin").param("colname", "root").param("colpassword", "admin"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        //测试密码错误
        mvc.perform(MockMvcRequestBuilders.post("/login/dologin").param("colname", "root").param("colpassword", "123"))
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"code\": 605,\n" +
                        "    \"message\": \"用户名或密码错误\",\n" +
                        "    \"data\": null\n" +
                        "}"));
    }
}
