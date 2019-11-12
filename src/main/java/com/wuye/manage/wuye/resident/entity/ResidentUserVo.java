package com.wuye.manage.wuye.resident.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ResidentUserVo对象", description="带创建人的住户表")
public class ResidentUserVo extends Resident {

    private String username;
}
