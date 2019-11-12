package com.wuye.manage.wuye.opening.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 开门记录表
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_opening_record")
@ApiModel(value="OpeningRecord对象", description="开门记录表")
public class OpeningRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "开门记录表主键")
    @TableId(value = "record_id", type = IdType.AUTO)
    private Integer recordId;

    @ApiModelProperty(value = "抓拍照片")
    private String photo;

    @ApiModelProperty(value = "抓拍时间")
    private LocalDateTime takeTime;

    @ApiModelProperty(value = "开门方式")
    private String openWay;

    @ApiModelProperty(value = "住户")
    private Integer ownerId;

    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "设备")
    private Integer devId;

    @ApiModelProperty(value = "所属小区")
    private Integer cid;

    @ApiModelProperty(value = "所属楼栋")
    private Integer floorId;

    @ApiModelProperty(value = "所属单元")
    private Integer unitId;

    @ApiModelProperty(value = "所属房屋")
    private Integer roomId;

    @ApiModelProperty(value = "呼叫方式")
    private String callWay;

    @ApiModelProperty(value = "呼叫用户名")
    private String callName;


}
