package com.wuye.manage.wuye.ad.entity;

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
 * 广告表
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_ad")
@ApiModel(value="Ad对象", description="广告表")
public class Ad implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "广告表主键")
    @TableId(value = "ad_id", type = IdType.AUTO)
    private Integer adId;

    @ApiModelProperty(value = "广告名称")
    private String adName;

    @ApiModelProperty(value = "广告类型")
    private String type;

    @ApiModelProperty(value = "广告分类")
    private String sort;

    @ApiModelProperty(value = "投放时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime putonTime;

    @ApiModelProperty(value = "投放位置")
    private String putonLocat;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "播放状态")
    private String playStatus;

    @ApiModelProperty(value = "小区id")
    private Integer cid;
}
