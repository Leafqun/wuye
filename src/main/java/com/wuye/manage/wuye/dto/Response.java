package com.wuye.manage.wuye.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="响应对象", description="响应")
public class Response<T> {

    @ApiModelProperty(value = "响应编码，0为成功")
    public String code = "0";

    @ApiModelProperty(value = "错误信息")
    public String errorMsg = "";

    @ApiModelProperty(value = "返回数据")
    public T data;

    public Response(T data) {
        this.data = data;
    }

    public Response(String code, String errorMsg) {
        this.code = code;
        this.errorMsg = errorMsg;
    }
}
