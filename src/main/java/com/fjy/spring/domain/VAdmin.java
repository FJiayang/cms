package com.fjy.spring.domain;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("SELECT * FROM v_admin")
@Data
public class VAdmin {
    @Id
    @Column(name = "coluserid")
    private Integer userid;

    private Integer adminid;

    private String coltime;

    private String colname;
}
