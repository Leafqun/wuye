package com.wuye.manage.wuye.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.entity.RoomQueryVo;
import com.wuye.manage.wuye.room.mapper.RoomMapper;
import com.wuye.manage.wuye.room.service.IRoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 房屋 服务实现类
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-29
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Resource
    private RoomMapper roomMapper;

    @Override
    public IPage<RoomQueryVo> getQueryPage(Page<RoomQueryVo> page, QueryWrapper<RoomQueryVo> qw, Integer cid) {
        return roomMapper.selectQueryPage(page, qw, cid);
    }
}
