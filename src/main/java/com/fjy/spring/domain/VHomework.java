package com.fjy.spring.domain;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Immutable
@Subselect("SELECT * FROM v_homework")
public class VHomework {
    @Id
    @Column(name = "workid")
    private Integer Id;

    @Column(name = "workname")
    private String Name;

    @Column(name = "worktime")
    private String Time;

    @Column(name = "colfileid")
    private Integer fileid;

    @Column(name = "workfolder")
    private String Folder;

    @Column(name = "coursename")
    private String courseName;

    @Column(name = "workremark")
    private String Remark;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFolder() {
        return Folder;
    }

    public void setFolder(String folder) {
        Folder = folder;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
