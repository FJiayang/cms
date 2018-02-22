package com.fjy.spring.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TbStudentlist {
    @Id
    private Integer listid;

    private String colstudentno;

    private String colrealname;

    private String sex;

    public Integer getListid() {
        return listid;
    }

    public void setListid(Integer listid) {
        this.listid = listid;
    }

    public String getColstudentno() {
        return colstudentno;
    }

    public void setColstudentno(String colstudentno) {
        this.colstudentno = colstudentno;
    }

    public String getColrealname() {
        return colrealname;
    }

    public void setColrealname(String colrealname) {
        this.colrealname = colrealname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
