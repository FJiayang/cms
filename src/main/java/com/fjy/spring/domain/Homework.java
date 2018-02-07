package com.fjy.spring.domain;

import javax.persistence.*;

@Entity
@Table(name = "tb_homework")
public class Homework {
    @Id
    @Column(name = "workid")
    @GeneratedValue
    private Integer Id;

    @Column(name = "workname")
    private String Name;

    @Column(name = "worktime")
    private String Time;

    @Column(name = "colfileid")
    private Integer fileid;

    @Column(name = "workfolder")
    private String Folder;

    @Column(name = "courseno")
    private Integer courseNo;

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

    public Integer getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Integer courseNo) {
        this.courseNo = courseNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
