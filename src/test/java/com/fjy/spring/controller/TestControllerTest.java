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
public class TestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHello()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello Spring"));
    }

   @Test
    public void testDataBase()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/name/root"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("root"));
    }

    @Test
    public void testDataBaseId()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/test/id/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
