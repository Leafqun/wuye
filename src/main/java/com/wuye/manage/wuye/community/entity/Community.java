package com.wuye.manage.wuye.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 小区表
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Community对象", description="小区表")
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "cid")
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    @ApiModelProperty(value = "小区名")
    private String name;

    @ApiModelProperty(value = "联系方式")
    private String contact;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "地标")
    private String landmark;

    @ApiModelProperty(value = "城市编码")
    private String code;

    @ApiModelProperty(value = "纬度")
    private Double lat;

    @ApiModelProperty(value = "经度")
    private Double lon;

    @ApiModelProperty(value = "状态, 0未入驻和审核1审核中2审核失败3入驻成功")
    private String status;

}
