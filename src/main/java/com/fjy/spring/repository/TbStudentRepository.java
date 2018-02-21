package com.fjy.spring.repository;

import com.fjy.spring.domain.TbStudent;
import com.fjy.spring.domain.VUserfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TbStudentRepository extends JpaRepository<TbStudent,Integer> {
}
