package com.fjy.spring.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

public class DownLoadControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void batDownload() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/download/downloadzip")
                .param("courseName","信息安全").param("Folder","第一次作业"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}