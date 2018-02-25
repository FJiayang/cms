package com.fjy.spring.domain;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("SELECT * FROM v_userinfo")
@Data
public class VUserinfo {
    @Id
    private Integer coluserid;

    private String colname;

    private String colemail;

    private String colstudentno;

    private String colrealname;
}
