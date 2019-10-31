package com.wuye.manage.wuye.community.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.community.entity.Community;
import com.wuye.manage.wuye.community.service.ICommunityService;
import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.exception.CrudException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 小区表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-31
 */
@Api(tags = "小区管理相关接口")
@RestController
@RequestMapping("/api/{version}")
public class CommunityController {

    @Resource
    private ICommunityService communityService;

    @ApiOperation("获取小区列表分页的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "name", value = "小区名，查询条件"),
            @ApiImplicitParam(name = "address", value = "小区地址，查询条件，支持模糊查询"),
    })
    @GetMapping("/communities/page")
    public Response<IPage<Community>> getFloorList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            String name,
            String address) {
        Page<Community> page = new Page<>(current, pageSize);
        QueryWrapper<Community> qw = new QueryWrapper<>();
        qw.eq(!StringUtils.isEmpty(name), "name", name);
        qw.like(!StringUtils.isEmpty(address), "address", address);
        IPage<Community> p = communityService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取小区详情的接口")
    @ApiImplicitParam(name = "cid", value = "小区id", required = true)
    @GetMapping("/communities/{cid}")
    public Response<Community> getFloor(@PathVariable Integer cid) {
        Community community = communityService.getById(cid);
        return new Response<>(community);
    }

    @ApiOperation("更改和新增小区的接口，以小区id区分")
    @ApiImplicitParam(name = "community", value = "小区对象")
    @PostMapping("/communities/save")
    public Response save(Community community) {
        if (!communityService.saveOrUpdate(community)) {
            throw new CrudException("103", "增加或者更改失败");
        }
        return new Response();
    }

    @ApiOperation("删除小区的接口")
    @ApiImplicitParam(name = "cid", value = "小区id", required = true)
    @DeleteMapping("/communities/{cid}")
    public Response delete(@PathVariable Integer cid) {
        if (!communityService.removeById(cid)) {
            throw new CrudException("104", cid + "小区删除失败");
        }
        return new Response();
    }
}
