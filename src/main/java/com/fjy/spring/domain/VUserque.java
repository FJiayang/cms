package com.fjy.spring.domain;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Immutable
@Subselect("SELECT * FROM v_userque")
public class VUserque {

    @Id
    @Column(name = "coluserid")
    private Integer userid;

    @Column(name = "colquestion")
    private String question;

    @Column(name = "colanswer")
    private String answer;

    @Column(name = "colname")
    private String name;

}
