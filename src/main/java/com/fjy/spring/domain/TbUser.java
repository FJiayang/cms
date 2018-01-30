package com.fjy.spring.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TbUser {
    @Id
    @GeneratedValue
    private Integer coluserid;

    private String colname;
    private String colpassword;
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

    public String getColpassword() {
        return colpassword;
    }

    public void setColpassword(String colpassword) {
        this.colpassword = colpassword;
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
        return "TbUser{" +
                "coluserid=" + coluserid +
                ", colname='" + colname + '\'' +
                ", colpassword='" + colpassword + '\'' +
                ", colemail='" + colemail + '\'' +
                ", colstudentno='" + colstudentno + '\'' +
                ", colrealname='" + colrealname + '\'' +
                '}';
    }
}
