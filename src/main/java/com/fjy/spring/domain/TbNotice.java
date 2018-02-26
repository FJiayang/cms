package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TbNotice {

    @Id
    @GeneratedValue
    private Integer noticeid;

    private Integer adminid;

    @Column(name = "noticecontent")
    private String noticeContent;

    @Column(name = "issuetime")
    private String issueTime;
}
