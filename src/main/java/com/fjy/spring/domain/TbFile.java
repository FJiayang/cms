package com.fjy.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TbFile {
    @Id
    @GeneratedValue
    private int colfileid;

    private int coluserid;

    private String coltime;

    private String colip;

    private String colrealname;

    private String colfilename;

    private String colfilesize;

    private String colfilepath;

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

    public int getColuserid() {
        return coluserid;
    }

    public void setColuserid(int coluserid) {
        this.coluserid = coluserid;
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
