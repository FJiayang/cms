package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TbStudentlist {
    @Id
    private Integer listid;

    private String colstudentno;

    private String colrealname;

    private String sex;

}
