package com.fjy.spring.service;

import com.fjy.spring.domain.VHomework;
import org.junit.Test;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class HomeworkServiceTest {

    @Test
    public void findAll() {
    }

    @Test
    public void findAllVHomework() {
    }

    @Test
    public void findById() {
        Format f = new SimpleDateFormat("yyyy-MM-dd");

        Date today = new Date();
        System.out.println("今天是:" + f.format(today));

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, -1);// 今天+1天

        Date tomorrow = c.getTime();
        System.out.println("昨天是:" + f.format(tomorrow));
    }

    @Test
    public void findAllVHomeworkAfterTime() {
        HomeworkService homeworkService = new HomeworkService();
        List<VHomework> homeworkList = homeworkService.findAllVHomeworkAfterTime("2018-05-28");
        if (homeworkList!=null){
            for (VHomework vHomework : homeworkList){
                System.out.println(vHomework.toString());
            }
        }
    }
}