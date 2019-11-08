package com.wuye.manage.wuye.user.service.impl;

import com.wuye.manage.wuye.user.entity.User;
import com.wuye.manage.wuye.user.mapper.UserMapper;
import com.wuye.manage.wuye.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
