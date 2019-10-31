package com.wuye.manage.wuye.floor.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.entity.FloorManagerVo;
import com.wuye.manage.wuye.floor.entity.FloorRoomVo;
import com.wuye.manage.wuye.floor.mapper.FloorMapper;
import com.wuye.manage.wuye.floor.service.IFloorService;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.mapper.RoomMapper;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.wuye.manage.wuye.unit.mapper.UnitMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 楼栋 服务实现类
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-24
 */
@Service
public class FloorServiceImpl extends ServiceImpl<FloorMapper, Floor> implements IFloorService {

    @Resource
    private FloorMapper floorMapper;

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private RoomMapper roomMapper;

    @Override
    public IPage<FloorRoomVo> selectPageWithNum(Page<FloorRoomVo> page, Wrapper wrapper, Integer cid) {
        return floorMapper.selectPageWithNum(page, wrapper, cid);
    }

    @Override
    public IPage<FloorManagerVo> selectPageWithManager(Page<FloorManagerVo> page, Wrapper wrapper, Integer cid) {
        return floorMapper.selectPageWithManager(page, wrapper, cid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Serializable id) {
        floorMapper.deleteById(id);
        unitMapper.delete(new QueryWrapper<Unit>().eq("fid", id));
        roomMapper.delete(new QueryWrapper<Room>().eq("fid", id));
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchDelete(Collection<? extends Serializable> ids) {
        for (Serializable id : ids) {
            floorMapper.deleteById(id);
            unitMapper.delete(new QueryWrapper<Unit>().eq("fid", id));
            roomMapper.delete(new QueryWrapper<Room>().eq("fid", id));
        }
        return true;
    }


}
