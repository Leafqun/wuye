package com.wuye.manage.wuye.unit.service;

import com.wuye.manage.wuye.unit.entity.Unit;
import com.baomidou.mybatisplus.extension.service.IService;

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

    public boolean delete(Serializable id);

    public boolean batchDelete(Collection<? extends Serializable> ids);
}
