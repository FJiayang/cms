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

}
