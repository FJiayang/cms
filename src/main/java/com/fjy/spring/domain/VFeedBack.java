package com.fjy.spring.domain;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Immutable
@Subselect("SELECT * FROM v_feedback")
@Data
public class VFeedBack {
    @Id
    @Column(name = "feedbackid")
    private Integer id;

    @Column(name = "colname")
    private String username;

    @Column(name = "feedbackcontent")
    private String content;

    @Column(name = "issuetime")
    private String time;

}
