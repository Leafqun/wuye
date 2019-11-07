package com.wuye.manage.wuye.room.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.mapper.FloorMapper;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.mapper.RoomMapper;
import com.wuye.manage.wuye.room.service.IRoomService;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.wuye.manage.wuye.unit.mapper.UnitMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

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

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private FloorMapper floorMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertOrUpdate(Room room) {
        if (room.getRoomId() == null) {
            roomMapper.insert(room);
            unitMapper.update(null, new UpdateWrapper<Unit>().setSql("total = total + 1").eq("uid", room.getUnitId()));
            floorMapper.update(null, new UpdateWrapper<Floor>().setSql("total = total + 1").eq("fid", room.getFloorId()));
        } else {
            roomMapper.updateById(room);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Serializable id) {
        Room room = roomMapper.selectById(id);
        if (room == null) {
            return false;
        }
        unitMapper.update(null, new UpdateWrapper<Unit>().setSql("total = total - 1").eq("uid", room.getUnitId()));
        floorMapper.update(null, new UpdateWrapper<Floor>().setSql("total = total - 1").eq("fid", room.getFloorId()));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIds(Collection<? extends Serializable> ids) {
        for (Serializable id : ids) {
            this.delete(id);
        }
        return true;
    }


}
