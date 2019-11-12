package com.wuye.manage.wuye.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuye.manage.wuye.user.entity.CommunityUser;
import com.wuye.manage.wuye.user.entity.User;
import com.wuye.manage.wuye.user.mapper.CommunityUserMapper;
import com.wuye.manage.wuye.user.mapper.UserMapper;
import com.wuye.manage.wuye.user.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    private CommunityUserMapper communityUserMapper;

    @Override
    public IPage<User> getPage(Page<User> page, QueryWrapper<User> qw, String cid) {
        return userMapper.selectPageByParam(page, qw, cid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(User user, Integer cid) {
        userMapper.insert(user);
        CommunityUser communityUser = new CommunityUser();
        communityUser.setCid(cid);
        communityUser.setUserId(user.getUserId());
        communityUserMapper.insert(communityUser);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer userId, Integer cid) {
        List<CommunityUser> list = communityUserMapper.selectList(new QueryWrapper<CommunityUser>().select("cid").eq("userId", userId));
        if (list.size() == 1) {
            userMapper.deleteById(userId);
        }
        communityUserMapper.delete(new QueryWrapper<CommunityUser>().eq("user_id", userId).eq("cid", cid));
        return false;
    }
}
