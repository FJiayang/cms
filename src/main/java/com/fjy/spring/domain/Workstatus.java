package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_workstatus")
@Data
public class Workstatus {
    @Id
    @GeneratedValue
    private Integer statusid;

    @Column(name = "coluserid")
    private Integer userid;

    @Column(name = "workid")
    private Integer workId;

    private Integer colstatus;
}
