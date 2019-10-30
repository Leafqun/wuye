package com.wuye.manage.wuye.floor.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value="Floor对象带房屋数量", description="带房屋数量的楼栋")
public class FloorRoomVo extends Floor {

    @ApiModelProperty(value = "房屋数量")
    private int num;
}
