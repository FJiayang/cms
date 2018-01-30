package com.fjy.spring.repository;

import com.fjy.spring.domain.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TbUserRepository extends JpaRepository<TbUser,Integer> {

    public Optional<TbUser> findByColname(String name);

}
