package com.wuye.manage.wuye.floor.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.entity.FloorManagerVo;
import com.wuye.manage.wuye.floor.entity.FloorRoomVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 楼栋 Mapper 接口
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-24
 */
public interface FloorMapper extends BaseMapper<Floor> {

    @Select("select a.*, count(b.fid) as num from floor a left join room b on a.fid = b.fid and b.cid=#{cid} ${ew.customSqlSegment} group by a.fid")
    IPage<FloorRoomVo> selectPageWithNum(Page<FloorRoomVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, @Param("cid") Integer cid);

    @Select("select a.*, b.manager_name from floor a left join community_manager b on a.cmid = b.cmid and b.cid = #{cid} ${ew.customSqlSegment}")
    IPage<FloorManagerVo> selectPageWithManager(Page<FloorManagerVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, @Param("cid") Integer cid);
}
