package com.fjy.spring.repository;

import com.fjy.spring.domain.TbStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TbStudentRepository extends JpaRepository<TbStudent,Integer> {

}
