package com.fjy.spring.repository;

import com.fjy.spring.domain.TbAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbAdminRepository extends JpaRepository<TbAdmin,Integer> {
    Optional<TbAdmin> findByUserid(Integer userid);
}
