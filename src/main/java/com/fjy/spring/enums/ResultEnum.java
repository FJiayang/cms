package com.fjy.spring.enums;

public enum ResultEnum {
    UNKOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"请求成功"),
    USER_NOTEXIST(101,"用户不存在"),
    UPDATE_ERROR(102,"更新失败"),
    DELETE_ERROR(103,"删除失败"),
    ADD_ERROR(104,"添加失败"),
    WRONGPASS(105,"用户名或密码错误"),
    ILLEGAL_ACCESS(106,"非法访问")
    ;
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
