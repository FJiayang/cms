package com.fjy.spring.domain;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Immutable
@Subselect("SELECT * FROM v_homework")
@Data
public class VHomework {
    @Id
    @Column(name = "workid")
    private Integer id;

    @Column(name = "workname")
    private String name;

    @Column(name = "worktime")
    private String time;

    @Column(name = "colfileid")
    private Integer fileid;

    @Column(name = "workfolder")
    private String folder;

    @Column(name = "coursename")
    private String courseName;

    @Column(name = "workremark")
    private String remark;

}
