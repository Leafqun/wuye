package com.wuye.manage.wuye.unit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 单元表
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Unit对象", description="单元表")
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "单元id")
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    @ApiModelProperty(value = "单元编号")
    private String unitCode;

    @ApiModelProperty(value = "创建人")
    private Integer mid;

    @ApiModelProperty(value = "楼栋id")
    private Integer fid;

    @ApiModelProperty(value = "小区id")
    private Integer cid;

    @ApiModelProperty(value = "楼层数")
    private Integer floorNum;

    @ApiModelProperty(value = "有无电梯")
    private String elevator;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;


}
