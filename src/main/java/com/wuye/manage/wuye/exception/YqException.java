package com.wuye.manage.wuye.exception;

import com.wuye.manage.wuye.enums.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YqException extends RuntimeException {

    private String code;

    private String errorMsg;

    public YqException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.errorMsg = errorEnum.getErrorMsg();
    }
}
