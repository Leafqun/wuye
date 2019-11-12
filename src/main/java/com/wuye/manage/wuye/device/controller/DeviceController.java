package com.wuye.manage.wuye.device.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.config.ApiVersion;
import com.wuye.manage.dto.Response;
import com.wuye.manage.exception.CrudException;
import com.wuye.manage.wuye.device.entity.Device;
import com.wuye.manage.wuye.device.service.IDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 门禁设备表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/api/{version}")
@Api(tags = "门禁设备管理接口")
@ApiVersion(1)
public class DeviceController {

    @Resource
    private IDeviceService deviceService;

    @ApiOperation("获取用户分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "cid", value = "小区id", required = true),
            @ApiImplicitParam(name = "floorId", value = "楼栋id，查询条件"),
            @ApiImplicitParam(name = "unitId", value = "单元id，查询条件"),
            @ApiImplicitParam(name = "devid", value = "设备id，查询条件"),
            @ApiImplicitParam(name = "status", value = "设备状态，查询条件"),
            @ApiImplicitParam(name = "type", value = "设备类型，查询条件"),
            @ApiImplicitParam(name = "isOpen", value = "启用状态，查询条件")
    })
    @PostMapping("/communities/{cid}/devices/page")
    public Response<IPage<Device>> getDeviceList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @PathVariable Integer cid, Integer floorId, Integer unitId,
            String type, String devid, String status, String isOpen) {
        Page<Device> page = new Page<>(current, pageSize);
        QueryWrapper<Device> qw = new QueryWrapper<>();
        qw.eq("cid", cid);
        qw.eq(!StringUtils.isEmpty(floorId), "floor_id", floorId);
        qw.eq(!StringUtils.isEmpty(unitId), "unit_id", unitId);
        qw.eq(!StringUtils.isEmpty(type), "type", type);
        qw.eq(!StringUtils.isEmpty(status), "status", status);
        qw.eq(!StringUtils.isEmpty(devid), "devid", devid);
        qw.eq(!StringUtils.isEmpty(isOpen), "is_open", isOpen);
        qw.orderByDesc("create_time");
        IPage<Device> p = deviceService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取门禁设备详情的接口")
    @ApiImplicitParam(name = "did", value = "门禁设备主键id", required = true)
    @PostMapping("/devices/{did}")
    public Response<Device> getDevice(@PathVariable Integer did) {
        Device device = deviceService.getById(did);
        return new Response<>(device);
    }

    @ApiOperation("保存门禁设备详情的接口")
    @PostMapping("/devices/save")
    public Response saveDevice(Device device) {
        if (!deviceService.saveOrUpdate(device)) {
            throw new CrudException("103", "新增或者更改失败");
        }
        return new Response<>();
    }


    @ApiOperation("删除门禁设备详情的接口")
    @ApiImplicitParam(name = "did", value = "门禁设备主键id", required = true)
    @PostMapping("/devices/delete/{did}")
    public Response deleteDevice(@PathVariable Integer did) {
        if (!deviceService.removeById(did)) {
            throw new CrudException("103", "删除失败");
        }
        return new Response<>();
    }
}
