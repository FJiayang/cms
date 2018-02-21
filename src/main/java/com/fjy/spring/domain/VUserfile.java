package com.fjy.spring.domain;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("SELECT * FROM v_userfile")
public class VUserfile {
    @Id
    private int colfileid;

    private String colstudentno;

    private String coltime;

    private String colip;

    private String colrealname;

    private String colfilename;

    private String colfilesize;

    private String colfilepath;
    @Column(name = "coursename")
    private String courseName;
    @Column(name = "workfolder")
    private String workFolder;


    @Override
    public String toString() {
        return "TbFile{" +
                "colip='" + colip + '\'' +
                ", colrealname='" + colrealname + '\'' +
                ", colfilename='" + colfilename + '\'' +
                ", colfilesize='" + colfilesize + '\'' +
                ", colfilepath='" + colfilepath + '\'' +
                '}';
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getWorkFolder() {
        return workFolder;
    }

    public void setWorkFolder(String workFolder) {
        this.workFolder = workFolder;
    }

    public String getColtime() {
        return coltime;
    }

    public void setColtime(String coltime) {
        this.coltime = coltime;
    }

    public int getColfileid() {
        return colfileid;
    }

    public void setColfileid(int colfileid) {
        this.colfileid = colfileid;
    }

    public String getColstudentno() {
        return colstudentno;
    }

    public void setColstudentno(String colstudentno) {
        this.colstudentno = colstudentno;
    }

    public String getColip() {
        return colip;
    }

    public void setColip(String colip) {
        this.colip = colip;
    }

    public String getColrealname() {
        return colrealname;
    }

    public void setColrealname(String colrealname) {
        this.colrealname = colrealname;
    }

    public String getColfilename() {
        return colfilename;
    }

    public void setColfilename(String colfilename) {
        this.colfilename = colfilename;
    }

    public String getColfilesize() {
        return colfilesize;
    }

    public void setColfilesize(String colfilesize) {
        this.colfilesize = colfilesize;
    }

    public String getColfilepath() {
        return colfilepath;
    }

    public void setColfilepath(String colfilepath) {
        this.colfilepath = colfilepath;
    }
}
