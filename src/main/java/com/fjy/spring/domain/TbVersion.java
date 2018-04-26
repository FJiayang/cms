package com.fjy.spring.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TbVersion {
    @Id
    @GeneratedValue
    private Integer versionid;

    private String date;

    private String content;

    private String version;

    private String user;

    public TbVersion() {
        super();
    }
}
