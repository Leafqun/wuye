package com.wuye.manage.wuye.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuye.manage.wuye.user.entity.User;
import com.wuye.manage.wuye.user.mapper.UserMapper;
import com.wuye.manage.wuye.user.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public IPage<User> getPage(Page<User> page, QueryWrapper<User> qw, String cid) {
        return userMapper.selectPageByParam(page, qw, cid);
    }
}
