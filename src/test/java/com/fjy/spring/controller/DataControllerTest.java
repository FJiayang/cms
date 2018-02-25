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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DataControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * 使用此单元测试前要注销拦截器，否则测试不通过
     * @throws Exception
     */
    @Test
    @Transactional
    public void adduserque() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/home/adduserque")
                .param("coluserid","53")
                .param("question","您母亲的姓名是？")
                .param("answer","YHM"))
                .andExpect(MockMvcResultMatchers.content().string("true"));
    }
}