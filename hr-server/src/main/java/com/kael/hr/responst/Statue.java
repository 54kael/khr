package com.kael.hr.responst;

public enum Statue {
    SUCCESS("00000", "操作成功"),
    FAILED("50000", "操作失败"),

    VALIDATE_FAILED("A0000", "参数检验失败"),
    LOGIN_FAILED("A0001","账号或密码错误"),

    UNAUTHORIZED("B0000", "暂未登录或token已经过期"),


    FORBIDDEN("C0000", "没有相关权限"),

    NOT_FOUND("D0000","账号不存在");
    private final String code;
    private final String message;

    private Statue(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
