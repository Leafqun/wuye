package com.wuye.manage.wuye.floor.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.entity.FloorRoomVo;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 楼栋 服务类
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-24
 */
public interface IFloorService extends IService<Floor> {

    public IPage<FloorRoomVo> selectPageWithNum(Page<FloorRoomVo> page, Wrapper wrapper, Integer cid);

    public boolean delete(Serializable id);

    public boolean batchDelete(Collection<? extends Serializable> ids);
}
