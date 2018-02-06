package com.fjy.spring.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TbLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logid;

    @Column(name = "coluserid")
    private Integer userid;

    private Timestamp coltime;

    private String colip;

    private String colheader;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Timestamp getColtime() {
        return coltime;
    }

    public void setColtime(Timestamp coltime) {
        this.coltime = coltime;
    }

    public String getColip() {
        return colip;
    }

    public void setColip(String colip) {
        this.colip = colip;
    }

    public String getColheader() {
        return colheader;
    }

    public void setColheader(String colheader) {
        this.colheader = colheader;
    }
}
