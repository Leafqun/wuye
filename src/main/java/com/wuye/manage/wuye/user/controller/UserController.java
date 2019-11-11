package com.wuye.manage.wuye.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.config.ApiVersion;
import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.exception.CrudException;
import com.wuye.manage.wuye.exception.ParamException;
import com.wuye.manage.wuye.user.entity.User;
import com.wuye.manage.wuye.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-08
 */
@RestController
@RequestMapping("/api/{version}")
@Api(tags = "用户管理相关接口")
@ApiVersion(1)
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation("登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "tag", value = "用户类型", required = true)
    })
    @PostMapping("/login")
    public Response login(@RequestParam String username, @RequestParam String password, @RequestParam String tag) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username).eq("tag", tag));
        if (user == null) {
            throw new ParamException("110", "用户名不存在");
        }
        if (!password.equals(user.getPassword())) {
            throw new ParamException("110", "密码错误");
        }
        return new Response();
    }

    @ApiOperation("获取用户分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "tag", value = "用户类型", required = true),
            @ApiImplicitParam(name = "cid", value = "小区或者代理id", required = true)
    })
    @PostMapping("/users/page")
    public Response<IPage<User>> getUserList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @RequestParam String tag, @RequestParam String cid) {
        Page<User> page = new Page<>(current, pageSize);
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("tag", tag);
        IPage<User> p = userService.getPage(page, qw, cid);
        return new Response<>(p);
    }

    @ApiOperation("获取用户详情的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @PostMapping("/users/{userId}")
    public Response<User> getUser(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        return new Response<>(user);
    }

    @ApiOperation("删除用户的接口")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    @PostMapping("/users/delete/{userId}")
    public Response<User> deleteUser(@PathVariable Integer userId) {
        if (!userService.removeById(userId)) {
            throw new CrudException("103", userId + "删除失败");
        }
        return new Response<>();
    }
}
