package com.fjy.spring.repository;

import com.fjy.spring.domain.TbUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TbUserRepository extends JpaRepository<TbUser,Integer> {

    public Optional<TbUser> findByColname(String name);

    @Modifying
    @Query("UPDATE TbUser u SET u.colpassword=?1 WHERE u.colname = ?2")
    public int updateColpasswordByColname(String password,String name);//返回更新的行数
}
