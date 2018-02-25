package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
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
    @Column(name = "coursename")
    private String courseName;
    @Column(name = "workfolder")
    private String workFolder;
}
