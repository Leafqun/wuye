package com.wuye.manage.wuye.resident.entity;

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
 * 住户表
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Resident对象", description="住户表")
@TableName("t_resident")
public class Resident implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "住户ID")
    @TableId(value = "resident_id", type = IdType.AUTO)
    private Integer residentId;

    @ApiModelProperty(value = "类型 1业主2业主成员3租客4商铺人员")
    private String type;

    @ApiModelProperty(value = "业主名称")
    private String name;

    @ApiModelProperty(value = "业主id")
    private Integer parentId;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "联系人手机号")
    private String phone;

    @ApiModelProperty(value = "创建人")
    private Integer userId;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "数据状态，详细参考c_status表，0, 在用 1失效")
    private String status;

    @ApiModelProperty(value = "小区id")
    private String cid;

    @ApiModelProperty(value = "楼栋id")
    private String floorId;

    @ApiModelProperty(value = "单元id")
    private String unitId;

    @ApiModelProperty(value = "房屋id")
    private String roomId;
}
