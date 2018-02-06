package com.fjy.spring.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TbTeacher {
    @Id
    @GeneratedValue
    private Integer teacherid;
    private Integer coluserid;
    private Integer coltime;

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }

    public Integer getColuserid() {
        return coluserid;
    }

    public void setColuserid(Integer coluserid) {
        this.coluserid = coluserid;
    }

    public Integer getColtime() {
        return coltime;
    }

    public void setColtime(Integer coltime) {
        this.coltime = coltime;
    }
}
