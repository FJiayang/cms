package com.fjy.spring.repository;

import com.fjy.spring.domain.VWorkDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkDetailRepository extends JpaRepository<VWorkDetail,Integer> {
}
