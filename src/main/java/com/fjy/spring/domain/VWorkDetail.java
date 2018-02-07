package com.fjy.spring.domain;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 映射视图实体
 */
@Entity
@Immutable
@Subselect("SELECT * FROM v_workdetail")
public class VWorkDetail {
    @Id
    private Integer workid;
    private Integer colfileid;
    private String workname;
    private String worktime;
    private String colfilename;
    private String coursename;
    private String workremark;
    private String workfolder;

    public Integer getWorkid() {
        return workid;
    }

    public void setWorkid(Integer workid) {
        this.workid = workid;
    }

    public Integer getColfileid() {
        return colfileid;
    }

    public void setColfileid(Integer colfileid) {
        this.colfileid = colfileid;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getColfilename() {
        return colfilename;
    }

    public void setColfilename(String colfilename) {
        this.colfilename = colfilename;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getWorkremark() {
        return workremark;
    }

    public void setWorkremark(String workremark) {
        this.workremark = workremark;
    }

    public String getWorkfolder() {
        return workfolder;
    }

    public void setWorkfolder(String workfolder) {
        this.workfolder = workfolder;
    }
}
