package com.wuye.manage.wuye.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuye.manage.wuye.user.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-08
 */
public interface IUserService extends IService<User> {

    public IPage<User> getPage(Page<User> page, QueryWrapper<User> qw, String cid);
}
