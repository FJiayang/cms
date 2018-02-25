package com.fjy.spring.domain;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 映射视图实体
 */
@Entity
@Immutable
@Subselect("SELECT * FROM v_workdetail")
@Data
public class VWorkDetail {
    @Id
    private Integer workid;
    private Integer colfileid;
    private String workname;
    private String worktime;
    private String colfilename;
    private String coursename;
    private String workremark;
    private String workfolder;
}
