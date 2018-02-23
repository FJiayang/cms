package com.fjy.spring.domain;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("SELECT * FROM v_userinfo")
public class VUserinfo {
    @Id
    private Integer coluserid;

    private String colname;

    private String colemail;

    private String colstudentno;

    private String colrealname;

    public Integer getColuserid() {
        return coluserid;
    }

    public void setColuserid(Integer coluserid) {
        this.coluserid = coluserid;
    }

    public String getColname() {
        return colname;
    }

    public void setColname(String colname) {
        this.colname = colname;
    }


    public String getColemail() {
        return colemail;
    }

    public void setColemail(String colemail) {
        this.colemail = colemail;
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

    @Override
    public String toString() {
        return "VUserinfo{" +
                "coluserid=" + coluserid +
                ", colname='" + colname + '\'' +
                ", colemail='" + colemail + '\'' +
                ", colstudentno='" + colstudentno + '\'' +
                ", colrealname='" + colrealname + '\'' +
                '}';
    }
}
