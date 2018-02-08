package com.fjy.spring.domain;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Immutable
@Subselect("SELECT * FROM v_course")
public class VCourse {
    @Id
    @Column(name = "courseno")
    private Integer courseNo;

    @Column(name = "coursename")
    private String courseName;

    @Column(name = "coursetime")
    private String courseTime;

    @Column(name = "colrealname")
    private String teacherusername;

    @Column(name = "colname")
    private String teacherrealname;

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

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getTeacherusername() {
        return teacherusername;
    }

    public void setTeacherusername(String teacherusername) {
        this.teacherusername = teacherusername;
    }

    public String getTeacherrealname() {
        return teacherrealname;
    }

    public void setTeacherrealname(String teacherrealname) {
        this.teacherrealname = teacherrealname;
    }
}
