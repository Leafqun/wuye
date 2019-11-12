package com.wuye.manage.wuye.resident.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.resident.entity.Resident;
import com.wuye.manage.wuye.resident.entity.ResidentUserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 住户表 Mapper 接口
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-11
 */
public interface ResidentMapper extends BaseMapper<Resident> {

    @Select("select a.*, b.username from t_resident a left join t_user b on a.user_id = b.user_id and b.tag = '3' ${ew.customSqlSegment}")
    public IPage<ResidentUserVo> selectPageWithUser(Page<ResidentUserVo> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
