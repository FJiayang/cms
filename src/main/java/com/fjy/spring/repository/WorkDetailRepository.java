package com.fjy.spring.repository;

import com.fjy.spring.domain.VWorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkDetailRepository extends JpaRepository<VWorkDetail,Integer> {
    /**
     * 找出指定时间之前的所有作业详情
     * @param worktime
     * @return
     */
    List<VWorkDetail> findAllByWorktimeAfter(String worktime);
}
