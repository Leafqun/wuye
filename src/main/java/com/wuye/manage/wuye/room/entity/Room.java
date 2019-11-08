package com.wuye.manage.wuye.room.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 房屋
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Room对象", description="房屋")
@TableName("t_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房屋id")
    @TableId(value = "room_id", type = IdType.AUTO)
    private Integer roomId;

    @ApiModelProperty(value = "房屋编号")
    private String roomCode;

    @ApiModelProperty(value = "单元id")
    private Integer unitId;

    @ApiModelProperty(value = "楼栋id")
    private Integer floorId;

    @ApiModelProperty(value = "小区id")
    private Integer cid;

    @ApiModelProperty(value = "楼层")
    private Integer layer;

    @ApiModelProperty(value = "业主id")
    private Integer residentId;

    @ApiModelProperty(value = "业主名称")
    private String name;

    @ApiModelProperty(value = "室")
    private Integer section;

    @ApiModelProperty(value = "户型")
    private String apartment;

    @ApiModelProperty(value = "建筑面积")
    private BigDecimal builtUpArea;

    @ApiModelProperty(value = "每平米单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "创建人")
    private Integer userId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "数据状态")
    private String status;

    @ApiModelProperty(value = "房屋状态")
    private String state;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
