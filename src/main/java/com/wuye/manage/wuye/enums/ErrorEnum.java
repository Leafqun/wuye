package com.wuye.manage.wuye.enums;

public enum ErrorEnum {
    //
    NOT_EXCEL("6", "上传文件不是excel文件"),

    //
    EMPTY_EXCEL("6", "excel文件不能为空"),

    EMPTY_PARAM("1", "请求参数为空"),

    INCOMPLETE_PARAM("2", "请求参数不全");

    String code;

    String errorMsg;

    ErrorEnum(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
