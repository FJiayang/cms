package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_feedback")
@Data
public class TbFeedBack {
    @Id
    @Column(name = "feedbackid")
    @GeneratedValue
    private Integer id;

    @Column(name = "coluserid")
    private Integer userid;

    @Column(name = "feedbackcontent")
    private String content;

    @Column(name = "issuetime")
    private String time;
}
