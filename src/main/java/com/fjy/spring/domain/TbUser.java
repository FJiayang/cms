package com.fjy.spring.domain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class TbUser {
    @Id
    @GeneratedValue
    private Integer coluserid;
    @NotNull(message = "用户名必填")
    private String colname;

    @Column(updatable=false)
    /*@NotNull(message = "密码不能为空")*/
    private String colpassword;
    private String colemail;
    @NotNull(message = "学号必填")
    private String colstudentno;
    @NotNull(message = "真实姓名必填")
    private String colrealname;
}
