package com.wuye.manage.wuye.room.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "房屋id")
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    @ApiModelProperty(value = "房屋编号")
    private String roomCode;

    @ApiModelProperty(value = "单元id")
    private Integer uid;

    @ApiModelProperty(value = "楼栋id")
    private Integer fid;

    @ApiModelProperty(value = "项目id")
    private Integer prid;

    @ApiModelProperty(value = "小区id")
    private Integer cid;

    @ApiModelProperty(value = "楼层")
    private Integer layer;

    @ApiModelProperty(value = "业主id")
    private Integer ownerId;

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
    private Integer mid;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "数据状态")
    private String status;

    @ApiModelProperty(value = "房屋状态")
    private String state;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
