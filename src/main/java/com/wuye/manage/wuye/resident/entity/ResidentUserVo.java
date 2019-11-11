package com.wuye.manage.wuye.resident.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value = "ResidentUserVo", description = "带创建人的住户信息")
public class ResidentUserVo extends Resident {

    private String username;
}
