package com.fjy.spring.domain;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Immutable
@Subselect("SELECT * FROM v_log ORDER BY coltime DESC LIMIT 0, 20")
@Data
public class VLog {
    @Id
    private Integer logid;

    private String colname;

    private String coltime;

    private String colip;

    private String colheader;
}
