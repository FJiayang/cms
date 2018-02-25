package com.fjy.spring.repository;

import com.fjy.spring.domain.VUserque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VUserqueRepository extends JpaRepository<VUserque,Integer> {

    public Optional<VUserque> findByName(String name);

}
