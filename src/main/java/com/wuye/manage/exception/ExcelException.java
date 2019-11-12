package com.wuye.manage.exception;

import com.wuye.manage.enums.ErrorEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ExcelException extends YqException {

    public ExcelException(String code, String errorMsg) {
        super(code, errorMsg);
    }

    public ExcelException(ErrorEnum errorEnum) {
        super(errorEnum.getCode(), errorEnum.getErrorMsg());
    }
}
