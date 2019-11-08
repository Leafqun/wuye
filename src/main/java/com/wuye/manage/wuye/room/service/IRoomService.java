package com.wuye.manage.wuye.room.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.entity.RoomQueryVo;

/**
 * <p>
 * 房屋 服务类
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-29
 */
public interface IRoomService extends IService<Room> {

    public IPage<RoomQueryVo> getQueryPage(Page<RoomQueryVo> page, QueryWrapper<RoomQueryVo> qw, Integer cid);
}
