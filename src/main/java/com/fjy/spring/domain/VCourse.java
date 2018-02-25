package com.fjy.spring.domain;

import lombok.Data;
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
@Data
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

}
