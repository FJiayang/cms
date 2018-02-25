package com.fjy.spring.domain;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TbTeacher {
    @Id
    @GeneratedValue
    private Integer teacherid;
    private Integer coluserid;
    private String coltime;
}
