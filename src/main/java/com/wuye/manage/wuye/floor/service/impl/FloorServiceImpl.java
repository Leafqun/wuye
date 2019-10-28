package com.wuye.manage.wuye.floor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.mapper.FloorMapper;
import com.wuye.manage.wuye.floor.service.IFloorService;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.wuye.manage.wuye.unit.mapper.UnitMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Serializable id) {
        floorMapper.deleteById(id);
        unitMapper.delete(new QueryWrapper<Unit>().eq("fid", id));
        return true;
    }
}
