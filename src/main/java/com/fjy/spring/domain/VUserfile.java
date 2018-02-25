package com.fjy.spring.domain;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("SELECT * FROM v_userfile")
@Data
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
}
