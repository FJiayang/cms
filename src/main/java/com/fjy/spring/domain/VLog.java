package com.fjy.spring.domain;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Immutable
@Subselect("SELECT *FROM v_log LIMIT 0, 20")
public class VLog {
    @Id
    private Integer logid;

    private String colname;

    private String coltime;

    private String colip;

    private String colheader;

    public Integer getLogid() {
        return logid;
    }

    public void setLogid(Integer logid) {
        this.logid = logid;
    }

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }

    public String getColtime() {
        return coltime;
    }

    public void setColtime(String coltime) {
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
