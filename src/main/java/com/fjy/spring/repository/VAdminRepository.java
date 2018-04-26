package com.fjy.spring.repository;

import com.fjy.spring.domain.VAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VAdminRepository extends JpaRepository<VAdmin,Integer> {
}
