package com.fjy.spring.domain;



import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class TbStudent {
    @Id
    @GeneratedValue
    private Integer studentid;

    @Column(name = "coluserid")
    private Integer userid;
    private String coltime;

}
