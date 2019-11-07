package com.wuye.manage.wuye.floor.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.entity.FloorUserVo;
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

    @Select("select a.*, count(b.fid) as num from t_floor a left join t_room b on a.floor_id = b.floor_id and b.cid=#{cid} ${ew.customSqlSegment} group by a.fid")
    IPage<FloorRoomVo> selectPageWithNum(Page<FloorRoomVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, @Param("cid") Integer cid);

    @Select("select a.*, c.username from t_floor a left join t_community_user b on b.cid = a.cid and b.cid = #{cid} left join t_user c on c.user_id = b.user_id ${ew.customSqlSegment}")
    IPage<FloorUserVo> selectPageWithUser(Page<FloorUserVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, @Param("cid") Integer cid);
}
