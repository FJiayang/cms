package com.fjy.spring.domain;

import javax.persistence.*;

@Entity
@Table(name = "tb_feedback")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
