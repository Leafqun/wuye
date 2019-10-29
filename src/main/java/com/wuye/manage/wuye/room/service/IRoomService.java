package com.wuye.manage.wuye.room.service;

import com.wuye.manage.wuye.room.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;

/**
 * <p>
 * 房屋 服务类
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-29
 */
public interface IRoomService extends IService<Room> {

    public boolean insertOrUpdate(Room room);

    public boolean delete(Serializable id);

    public boolean deleteByIds(Collection<? extends Serializable> ids);
}
