package com.wuye.manage.wuye.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ParamException extends YqException {

    public ParamException(String code, String errorMsg) {
        super(code, errorMsg);
    }
}
