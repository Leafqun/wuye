package com.wuye.manage.wuye.unit.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuye.manage.wuye.unit.entity.UnitRoomVo;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 单元表 服务类
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-28
 */
public interface IUnitService extends IService<Unit> {

    public IPage<UnitRoomVo> selectPageWithNum(Page<UnitRoomVo> page, Wrapper<UnitRoomVo> wrapper, Integer cid);

    public boolean delete(Serializable id);

    public boolean batchDelete(Collection<? extends Serializable> ids);
}
