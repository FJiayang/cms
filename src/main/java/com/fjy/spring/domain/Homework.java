package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_homework")
@Data
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

    @Column(name = "pre")
    private String filePrefix;

    @Column(name = "suf")
    private String fileSuffix;
}
