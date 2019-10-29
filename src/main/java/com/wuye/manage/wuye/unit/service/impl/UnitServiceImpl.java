package com.wuye.manage.wuye.unit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.mapper.RoomMapper;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.wuye.manage.wuye.unit.mapper.UnitMapper;
import com.wuye.manage.wuye.unit.service.IUnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 单元表 服务实现类
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-28
 */
@Service
public class UnitServiceImpl extends ServiceImpl<UnitMapper, Unit> implements IUnitService {

    @Resource
    private UnitMapper unitMapper;

    @Resource
    private RoomMapper roomMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Serializable id) {
        unitMapper.deleteById(id);
        roomMapper.delete(new QueryWrapper<Room>().eq("uid", id));
        return true;
    }

    @Override
    public boolean batchDelete(Collection<? extends Serializable> ids) {
        for (Serializable id : ids) {
            unitMapper.deleteById(id);
            roomMapper.delete(new QueryWrapper<Room>().eq("uid", id));
        }
        return true;
    }
}
