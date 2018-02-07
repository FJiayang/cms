package com.fjy.spring.repository;

import com.fjy.spring.domain.Homework;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HomeworkRepository extends JpaRepository<Homework,Integer> {
}
