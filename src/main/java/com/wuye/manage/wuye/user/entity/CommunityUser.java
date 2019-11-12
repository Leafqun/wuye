package com.wuye.manage.wuye.user.entity;

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
 * 小区代理上用户表
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CommunityUser对象", description="小区代理上用户表")
@TableName("t_community_user")
public class CommunityUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小区代理用户表主键")
    @TableId(value = "cu_id", type = IdType.AUTO)
    private Integer cuId;

    @ApiModelProperty(value = "小区id")
    private Integer cid;

    @ApiModelProperty(value = "用户id")
    private Integer userId;


}
