package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class TbCourse {
    @Id
    @Column(name = "courseno")
    @GeneratedValue
    private Integer courseNo;

    @Column(name = "coursename")
    private String courseName;

    @Column(name = "coursetime")
    private Timestamp courseTime;

    private Integer teacherid;
}
