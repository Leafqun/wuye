package com.wuye.manage.exception;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CrudException extends YqException {

    public CrudException(String code, String errorMsg) {
        super(code, errorMsg);
    }
}
