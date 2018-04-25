package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TbLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logid;

    @Column(name = "coluserid")
    private Integer userid;

    private String coltime;

    private String colip;

    private String colheader;

    @Column(name = "requesturl")
    private String requestURL;

}
