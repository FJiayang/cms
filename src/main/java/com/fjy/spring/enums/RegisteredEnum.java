package com.fjy.spring.enums;

import lombok.Getter;

@Getter
public enum RegisteredEnum {
    REGISTERED(1,"该用户已注册"),
    UNREGISTERED(0,"该用户未注册"),
    FORBIDDEN(2,"该用户已被列入黑名单")
    ;
    private Integer code;
    private String msg;

    RegisteredEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
