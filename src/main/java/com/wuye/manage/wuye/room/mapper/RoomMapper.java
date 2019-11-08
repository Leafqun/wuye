package com.wuye.manage.wuye.room.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.entity.RoomQueryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 房屋 Mapper 接口
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-29
 */
public interface RoomMapper extends BaseMapper<Room> {

    @Select("select a.*, b.unit_code, c.floor_code, c.name as floorName, d.name as owner_name from t_room a left join t_unit b on b.unit_id = a.unit_id and b.cid = #{cid} left join t_floor c on c.floor_id = b.floor_id and c.cid = #{cid} left join t_resident d on d.resident_id = a.resident_id ${ew.customSqlSegment}")
    public IPage<RoomQueryVo> selectQueryPage(Page<RoomQueryVo> page, @Param(Constants.WRAPPER) Wrapper wrapper, Integer cid);
}
