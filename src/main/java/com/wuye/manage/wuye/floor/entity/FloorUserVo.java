package com.wuye.manage.wuye.floor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "FloorUserVo对象", description = "带创建人的楼栋信息")
public class FloorUserVo extends Floor{

    @ApiModelProperty(value = "创建人姓名")
    private String username;
}
