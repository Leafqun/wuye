package com.wuye.manage.wuye.floor.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
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
import java.time.LocalDateTime;

/**
 * <p>
 * 楼栋
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Floor对象", description="楼栋")
@TableName("t_floor")
public class Floor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "楼栋id")
    @TableId(value = "floor_id", type = IdType.AUTO)
    private Integer floorId;

    @ApiModelProperty(value = "小区id")
    private Integer cid;

    @ApiModelProperty(value = "楼栋编号")
    @Excel(name = "楼栋编号")
    private String floorCode;

    @ApiModelProperty(value = "楼栋名称")
    @Excel(name = "楼栋名称")
    private String name;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private Integer userId;

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注")
    private String remark;

    @ApiModelProperty(value = "状态")
    private String status;


}
