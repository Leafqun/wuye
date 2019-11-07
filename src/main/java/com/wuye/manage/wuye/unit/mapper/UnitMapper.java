package com.wuye.manage.wuye.unit.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuye.manage.wuye.unit.entity.UnitUserVo;
import com.wuye.manage.wuye.unit.entity.UnitRoomVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 单元表 Mapper 接口
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-28
 */
public interface UnitMapper extends BaseMapper<Unit> {

    @Select("select a.*, count(uid) as num from unit a left join room b on b.uid = a.uid and b.cid=#{cid} ${ew.customSqlSegment} group by a.uid")
    public IPage<UnitRoomVo> selectPageWithNum(Page<UnitRoomVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, @Param("cid") Integer cid);

    @Select("select a.*, b.username from t_unit a left join t_community_user b on b.cid = a.cid and b.cid = #{cid} left join community_manager c on c.user_id = b.user_id")
    public IPage<UnitUserVo> selectPageWithUser(Page<UnitUserVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, @Param("cid") Integer cid);
}
