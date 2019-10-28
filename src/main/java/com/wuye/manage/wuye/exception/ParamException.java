package com.wuye.manage.wuye.exception;

import com.wuye.manage.wuye.enums.ErrorEnum;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ParamException extends YqException {

    public ParamException(String code, String errorMsg) {
        super(code, errorMsg);
    }

    public ParamException(ErrorEnum errorEnum) {
        super(errorEnum.getCode(), errorEnum.getErrorMsg());
    }

}
