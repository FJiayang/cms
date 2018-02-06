package com.fjy.spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class TbCourse {
    @Id
    @GeneratedValue
    private Integer courseNo;

    private String courseName;

    private Timestamp courseTime;

    private Integer teacherid;

    public Integer getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Integer courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Timestamp getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Timestamp courseTime) {
        this.courseTime = courseTime;
    }

    public Integer getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(Integer teacherid) {
        this.teacherid = teacherid;
    }
}
