package com.wuye.manage.wuye.floor.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.common.ExcelUtils;
import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.service.IFloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 楼栋 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-24
 */
@RestController
@Api(tags = "楼栋管理相关接口")
@RequestMapping("/floor")
@Slf4j
public class FloorController {

    @Autowired
    private IFloorService floorService;

    @ApiOperation("获取楼栋列表分页的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "小区id", required = true),
            @ApiImplicitParam(name = "current", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "floorCode", value = "楼栋编码，查询条件"),
            @ApiImplicitParam(name = "name", value = "楼栋名，查询条件")
    })
    @GetMapping("/getFloorList")
    public Response<IPage<Floor>> getFloorList(@RequestParam Integer cid, @RequestParam Integer current, Integer pageSize, String floorCode, String name) {
        Page<Floor> page = new Page<>(current, pageSize == null ? 15 : pageSize);
        QueryWrapper<Floor> qw = new QueryWrapper<>();
        qw.eq(!StringUtils.isEmpty(floorCode), "floor_code", floorCode);
        qw.eq(!StringUtils.isEmpty(name), "name", name);
        IPage<Floor> p = floorService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取楼栋详情的接口")
    @ApiImplicitParam(name = "fid", value = "楼栋id", required = true)
    @GetMapping("/getFloor")
    public Response<Floor> getFloor(@RequestParam Integer fid) {
        Floor floor = floorService.getById(fid);
        return new Response<>(floor);
    }

    @ApiOperation("更改楼栋的接口")
    @ApiImplicitParam(name = "floor", value = "楼栋对象")
    @PostMapping("/update")
    public Response updateFloor(Floor floor) {
        if (floor.getFid() == null) {
            return new Response("1", "请求参数cid为空");
        }
        floor.setUpdateTime(LocalDateTime.now());
        if (!floorService.updateById(floor)) {
            return new Response("2", "更新失败");
        }
        return new Response();
    }

    @ApiOperation("插入楼栋的接口")
    @ApiImplicitParam(name = "floor", value = "楼栋对象")
    @PostMapping("/insert")
    public Response insertFloor(Floor floor) {
        floor.setCreateTime(LocalDateTime.now());
        floor.setUpdateTime(LocalDateTime.now());
        if (!floorService.save(floor)) {
            return new Response("3", "插入失败");
        }
        return new Response();
    }

    @ApiOperation("删除楼栋的接口")
    @ApiImplicitParam(name = "fid", value = "楼栋id", required = true)
    @GetMapping("delete")
    public Response deleteFloor(@RequestParam Integer fid) {
        if (!floorService.removeById(fid)) {
            return new Response("4", "删除失败");
        }
        return new Response();
    }

    @ApiOperation("批量删除楼栋的接口")
    @ApiImplicitParam(name = "fids", value = "楼栋id数组", required = true)
    @PostMapping("/batchDelete")
    public Response batchDelete(@RequestParam Integer[] fid) {
        if (!floorService.removeByIds(Arrays.asList(fid))) {
            return new Response("4", "批量删除失败");
        }
        return new Response();
    }

    @PostMapping("/batchInsert")
    public Response batchInsert(@RequestParam Integer cid, @RequestParam Integer mid, @RequestParam MultipartFile file) {
        List<Floor> floorList = ExcelUtils.importExcel(file, 0, 1, Floor.class);
        for (Floor floor : floorList) {
            floor.setCid(cid);
            floor.setMid(mid);
            floor.setCreateTime(LocalDateTime.now());
            floor.setUpdateTime(LocalDateTime.now());
        }
        if (!floorService.saveBatch(floorList)) {
            return new Response("3", "批量插入失败");
        }
        return new Response();
    }
}
