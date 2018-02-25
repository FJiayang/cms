package com.fjy.spring.enums;

public enum ResultEnum {
    UNKOWN_ERROR(-1,"未知错误"),
    SUCCESS(0,"请求成功"),
    USER_NOTEXIST(601,"用户不存在"),
    UPDATE_ERROR(602,"更新失败"),
    DELETE_ERROR(603,"删除失败"),
    ADD_ERROR(604,"添加失败"),
    WRONGPASS(605,"用户名或密码错误"),
    ILLEGAL_ACCESS(606,"非法访问"),
    WRONG_FORM(607,"表单错误"),
    EMPTY_DATA(608,"无数据"),
    ID_NULLPOINT(609,"id为空"),
    EMPTY_QUESTION(610,"该用户未设置密保问题"),
    QUESTION_ERROR(611,"问题与答案不匹配"),

    ;
    private Integer code;
    private String msg;
    private String data;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;

    }

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
