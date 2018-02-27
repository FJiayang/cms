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

/*
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DataControllerTest {

    @Autowired
    private MockMvc mvc;

    */
/**
     * 使用此单元测试前要注销拦截器，否则测试不通过
     * @throws Exception
     *//*

    @Test
    @Transactional
    public void adduserque() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/home/adduserque")
                .param("coluserid","53")
                .param("question","您母亲的姓名是？")
                .param("answer","YHM"))
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }

    */
/**
     *测试找回密码
     * @throws Exception
     *//*

    @Test
    public void findUserQue() throws Exception{
        //测试问题和答案均正确
        mvc.perform(MockMvcRequestBuilders.get("/finduserque")
                .param("name","root")
                .param("question","您配偶的姓名是？")
                .param("answer","abc"))
                .andExpect(MockMvcResultMatchers.content().string("true"));

        //测试问题错误
        mvc.perform(MockMvcRequestBuilders.get("/finduserque")
                .param("name","root")
                .param("question","您配偶的姓名是")
                .param("answer","abc"))
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"code\": 611,\n" +
                        "    \"message\": \"问题与答案不匹配\",\n" +
                        "    \"data\": null\n" +
                        "}"));

        //测试问题正确,答案错误
        mvc.perform(MockMvcRequestBuilders.get("/finduserque")
                .param("name","root")
                .param("question","您配偶的姓名是？")
                .param("answer","a"))
                .andExpect(MockMvcResultMatchers.content().string("false"));

        //未设置问题
        mvc.perform(MockMvcRequestBuilders.get("/finduserque")
                .param("name","roo")
                .param("question","您配偶的姓名是？")
                .param("answer","a"))
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"code\": 610,\n" +
                        "    \"message\": \"该用户未设置密保问题\",\n" +
                        "    \"data\": null\n" +
                        "}"));
    }

    */
/**
     * 测试忘记密码操作
     * @throws Exception
     *//*

    @Test
    @Transactional
    public void resetPass() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/resetPass")
                .param("name","root")
                .param("question","您配偶的姓名是？")
                .param("answer","abc")
                .param("password","admin"))
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }
}*/
