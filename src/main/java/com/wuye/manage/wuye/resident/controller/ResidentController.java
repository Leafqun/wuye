package com.wuye.manage.wuye.resident.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.config.ApiVersion;
import com.wuye.manage.dto.Response;
import com.wuye.manage.exception.CrudException;
import com.wuye.manage.wuye.resident.entity.Resident;
import com.wuye.manage.wuye.resident.entity.ResidentUserVo;
import com.wuye.manage.wuye.resident.service.IResidentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 住户表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-11
 */
@RestController
@RequestMapping("/api/{version}")
@Api(tags = "住户管理相关接口")
@ApiVersion(1)
public class ResidentController {

    @Resource
    private IResidentService residentService;

    @ApiOperation("获取某个小区住户列表分页的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "cid", value = "小区id", required = true),
            @ApiImplicitParam(name = "floor_id", value = "楼栋编码，查询条件"),
            @ApiImplicitParam(name = "unit_id", value = "单元id，查询条件"),
            @ApiImplicitParam(name = "room_id", value = "房屋id，查询条件"),
            @ApiImplicitParam(name = "type", value = "住户类型，查询条件")
    })
    @PostMapping("/communities/{cid}/residents/page")
    public Response<IPage<ResidentUserVo>> getResidentList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @PathVariable Integer cid,
            Integer floorId, Integer unitId, Integer roomId, String type) {
        Page<ResidentUserVo> page = new Page<>(current, pageSize);
        QueryWrapper<ResidentUserVo> qw = new QueryWrapper<>();
        qw.eq("a.cid", cid);
        qw.eq(floorId != null, "a.floor_id", floorId);
        qw.eq(unitId != null, "a.unit_id", unitId);
        qw.eq(roomId != null, "a.room_id", roomId);
        qw.eq(!StringUtils.isEmpty(type), "a.type", type);
        IPage<ResidentUserVo> p = residentService.getResidentPageWithUser(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取住户详情的接口")
    @ApiImplicitParam(name = "residentId", value = "住户id", required = true)
    @PostMapping("/residents/{residentId}")
    public Response<Resident> getResident(@PathVariable Integer residentId) {
        Resident resident = residentService.getById(residentId);
        return new Response<>(resident);
    }

    @ApiOperation("保存住户信息的接口")
    @PostMapping("/residents/save")
    public Response saveResident(Resident resident) {
        if (!residentService.saveOrUpdate(resident)) {
            throw new CrudException("103", "更改或者添加失败");
        }
        return new Response<>();
    }

    @ApiOperation("删除住户信息的接口")
    @ApiImplicitParam(name = "residentId", value = "住户id", required = true)
    @PostMapping("/residents/delete/{residentId}")
    public Response delete(@PathVariable Integer residentId) {
        if (!residentService.removeById(residentId)) {
            throw new CrudException("104", residentId + "删除失败");
        }
        return new Response<>();
    }
}
