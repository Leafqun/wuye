package com.wuye.manage.wuye.floor.service;

import com.wuye.manage.wuye.floor.entity.Floor;
import com.baomidou.mybatisplus.extension.service.IService;

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

    public boolean delete(Serializable id);

    public boolean batchDelete(Collection<? extends Serializable> ids);
}
