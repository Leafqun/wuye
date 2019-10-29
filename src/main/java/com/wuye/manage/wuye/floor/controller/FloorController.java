package com.wuye.manage.wuye.floor.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.common.ExcelUtils;
import com.wuye.manage.wuye.config.ApiVersion;
import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.enums.ErrorEnum;
import com.wuye.manage.wuye.exception.CrudException;
import com.wuye.manage.wuye.exception.ParamException;
import com.wuye.manage.wuye.floor.entity.Floor;
import com.wuye.manage.wuye.floor.service.IFloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
@RequestMapping("/api/{version}")
@Slf4j
@ApiVersion(1)
public class FloorController {

    @Resource
    private IFloorService floorService;

    @ApiOperation("获取楼栋列表分页的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "floorCode", value = "楼栋编码，查询条件"),
            @ApiImplicitParam(name = "name", value = "楼栋名，查询条件")
    })
    @GetMapping("/floors/page")
    public Response<IPage<Floor>> getFloorList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            String floorCode,
            String name) {
        Page<Floor> page = new Page<>(current, pageSize);
        QueryWrapper<Floor> qw = new QueryWrapper<>();
        qw.eq(!StringUtils.isEmpty(floorCode), "floor_code", floorCode);
        qw.like(!StringUtils.isEmpty(name), "name", name);
        IPage<Floor> p = floorService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取楼栋详情的接口")
    @ApiImplicitParam(name = "fid", value = "楼栋id", required = true)
    @GetMapping("/floors/{fid}")
    public Response<Floor> getFloor(@RequestParam @PathVariable Integer fid) {
        Floor floor = floorService.getById(fid);
        return new Response<>(floor);
    }

    @ApiOperation("更改和新增楼栋的接口，以楼栋id区分")
    @ApiImplicitParam(name = "floor", value = "楼栋对象")
    @PostMapping("/floors/save")
    public Response saveFloor(Floor floor) {
        // fid == null 添加，否则为删除
        if (floor.getFid() == null) {
            if (floor.getCid() == null || StringUtils.isEmpty(floor.getFloorCode())) {
                throw new ParamException(ErrorEnum.INCOMPLETE_PARAM);
            }
            Floor floor1 = floorService.getOne(new QueryWrapper<Floor>().eq("cid", floor.getCid()).eq("floor_code", floor.getFloorCode()).last("limit 1"));
            if (floor1 != null) {
                throw new CrudException("100", "楼栋编号已存在");
            }
            floor.setCreateTime(LocalDateTime.now());
        } else {
            if (floor.getFloorCode() != null) {
                Floor f = floorService.getById(floor.getFid());
                Floor floor2 = floorService.getOne(new QueryWrapper<Floor>().eq("cid", f.getCid()).eq("floor_code", floor.getFloorCode()));
                if (floor2 != null) {
                    throw new CrudException("100", "楼栋编号已存在");
                }
            }
        }
        floor.setUpdateTime(LocalDateTime.now());
        if (!floorService.saveOrUpdate(floor)) {
            throw new CrudException("100", "更改或者新增失败");
        }
        return new Response();
    }

    @ApiOperation("删除楼栋的接口")
    @ApiImplicitParam(name = "fid", value = "楼栋id", required = true)
    @DeleteMapping("/floors/{fid}")
    public Response deleteFloor(@PathVariable Integer fid) {
        floorService.delete(fid);
        return new Response();
    }

    @ApiOperation("批量删除楼栋的接口")
    @ApiImplicitParam(name = "fids", value = "楼栋id数组", required = true)
    @DeleteMapping("/floors")
    public Response batchDelete(@RequestParam Integer[] fid) {
        if (!floorService.batchDelete(Arrays.asList(fid))) {
            throw new CrudException("104", "批量删除失败");
        }
        return new Response();
    }

    @ApiOperation("批量添加楼栋的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "小区id", required = true),
            @ApiImplicitParam(name = "mid", value = "管理员id"),
            @ApiImplicitParam(name = "file", value = "上传的Excel文件"),
    })
    @PostMapping("/floors/batch-insert")
    public Response batchInsert(
            @RequestParam Integer cid,
            Integer mid,
            @RequestParam MultipartFile file) {
        List<Floor> floorList = ExcelUtils.importExcel(file, 0, 1, Floor.class);
        int i = 1;
        for (Floor floor : floorList) {
            if (StringUtils.isEmpty(floor.getFloorCode())) {
                throw new ParamException("3", "第" + i + "行数据楼栋编码不存在");
            }
            Floor floor1 = floorService.getOne(new QueryWrapper<Floor>().eq("cid", cid).eq("floor_code", floor.getFloorCode()));
            if (floor1 != null) {
                throw new ParamException("3", "第" + i + "行数据楼栋编码已存在");
            }
            floor.setCid(cid);
            floor.setMid(mid);
            floor.setCreateTime(LocalDateTime.now());
            floor.setUpdateTime(LocalDateTime.now());
            i++;
        }
        if (!floorService.saveBatch(floorList)) {
            throw new CrudException("105", "批量插入失败");
        }
        return new Response();
    }

    @ApiOperation("获取对应小区所有楼栋的接口")
    @ApiImplicitParam(name = "cid", value = "小区id", required = true)
    @GetMapping("communities/{cid}/floors/list")
    public Response<List<Floor>> getAllFloors(@PathVariable Integer cid) {
        List<Floor> floorList = floorService.list(new QueryWrapper<Floor>().select("fid, floor_code, name").eq("cid", cid));
        return new Response<>(floorList);
    }
}
