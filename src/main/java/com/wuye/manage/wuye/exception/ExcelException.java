package com.wuye.manage.wuye.exception;

import com.wuye.manage.wuye.enums.ErrorEnum;
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
