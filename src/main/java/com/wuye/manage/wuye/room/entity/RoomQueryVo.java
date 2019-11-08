package com.wuye.manage.wuye.room.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ApiModel(value="Room查询对象", description="房屋查询数据")
public class RoomQueryVo extends Room {

    @ApiModelProperty(value = "单元编码")
    private String unitCode;

    @ApiModelProperty(value = "楼栋编码")
    private String floorCode;

    @ApiModelProperty(value = "楼栋名")
    private String floorName;

    @ApiModelProperty(value = "业主名")
    private String ownerName;
}
