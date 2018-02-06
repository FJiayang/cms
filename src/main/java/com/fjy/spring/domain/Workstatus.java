package com.fjy.spring.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tb_workstatus")
public class Workstatus {
    @Id
    @GeneratedValue
    private Integer statusid;

    @Column(name = "coluserid")
    private Integer userid;

    private Integer workId;

    private Integer colstatus;

    public Integer getStatusid() {
        return statusid;
    }

    public void setStatusid(Integer statusid) {
        this.statusid = statusid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getColstatus() {
        return colstatus;
    }

    public void setColstatus(Integer colstatus) {
        this.colstatus = colstatus;
    }
}
