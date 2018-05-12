package com.fjy.spring.repository;

import com.fjy.spring.domain.VHomework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VHomeworkRepository extends JpaRepository<VHomework,Integer> {
    /**
     * 找出指定时间之前的所有作业
     * @param destime
     * @return
     */
    List<VHomework> findAllByTimeAfter(String destime);
}
