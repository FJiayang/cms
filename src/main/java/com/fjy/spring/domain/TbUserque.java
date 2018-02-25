package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class TbUserque {

    @Id
    @Column(name = "coluserid")
    private Integer userid;

    @Column(name = "colquestion")
    private String question;

    @Column(name = "colanswer")
    private String answer;

}
