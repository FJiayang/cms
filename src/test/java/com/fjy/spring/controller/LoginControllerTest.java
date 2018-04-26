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
public class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void toLoginPage() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void doLogin() throws Exception {
        //测试正常登录
        mvc.perform(MockMvcRequestBuilders.post("/login/dologin").param("colname", "root").param("colpassword", "root"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        //测试密码错误
        mvc.perform(MockMvcRequestBuilders.post("/login/dologin").param("colname", "root").param("colpassword", "123"))
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"code\": 105,\n" +
                        "    \"message\": \"用户名或密码错误\",\n" +
                        "    \"data\": null\n" +
                        "}"));
    }
}
