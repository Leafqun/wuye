package com.wuye.manage.wuye.unit.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value="Unit对象带房屋数量", description="带房屋数量的单元")
public class UnitRoomVo extends Unit{

    @ApiModelProperty(value = "房屋数量")
    private int num;
}
