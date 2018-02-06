package com.fjy.spring.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TbStudent {
    @Id
    @GeneratedValue
    private Integer studentid;

    @Column(name = "coluserid")
    private Integer userid;
    private Integer coltime;

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getColtime() {
        return coltime;
    }

    public void setColtime(Integer coltime) {
        this.coltime = coltime;
    }
}
