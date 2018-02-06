package com.fjy.spring.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TbAdmin {
    @Id
    @GeneratedValue
    private Integer adminid;

    @Column(name = "coluserid")
    private Integer userid;
    private Integer coltime;

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
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
