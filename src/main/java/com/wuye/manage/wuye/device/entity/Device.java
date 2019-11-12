package com.wuye.manage.wuye.device.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 门禁设备表
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_device")
@ApiModel(value="Device对象", description="门禁设备表")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "门禁设备主键")
    @TableId(value = "did", type = IdType.AUTO)
    private Integer did;

    @ApiModelProperty(value = "设备id")
    private String devId;

    @ApiModelProperty(value = "设备名")
    private String devName;

    @ApiModelProperty(value = "门禁类型")
    private String type;

    @ApiModelProperty(value = "设备状态")
    private String status;

    @ApiModelProperty(value = "所属楼栋")
    private Integer floorId;

    @ApiModelProperty(value = "所属单元")
    private Integer unitId;

    @ApiModelProperty(value = "所属小区")
    private Integer cid;

    @ApiModelProperty(value = "app版本")
    private String appVersion;

    @ApiModelProperty(value = "固件版本")
    private String firmwareVersion;

    @ApiModelProperty(value = "启用状态")
    private String isOpen;

    @ApiModelProperty(value = "防拆开关")
    private String devSwitch;


}
