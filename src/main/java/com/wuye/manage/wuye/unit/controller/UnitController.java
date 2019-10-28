package com.wuye.manage.wuye.unit.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.enums.ErrorEnum;
import com.wuye.manage.wuye.exception.CrudException;
import com.wuye.manage.wuye.exception.ParamException;
import com.wuye.manage.wuye.floor.service.IFloorService;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.wuye.manage.wuye.unit.service.IUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 单元表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-28
 */
@RestController
@RequestMapping("/unit")
@Api(tags = "单元管理相关接口")
public class UnitController {

    @Resource
    private IUnitService unitService;

    @Resource
    private IFloorService floorService;

    @ApiOperation("获取单元列表分页的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "unitCode", value = "单元编码，查询条件"),
            @ApiImplicitParam(name = "cid", value = "小区id，查询条件", required = true),
            @ApiImplicitParam(name = "fid", value = "楼栋id，查询条件")
    })
    @GetMapping("/getUnitList")
    public Response<IPage<Unit>> getUnitList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            String unitCode, Integer cid, Integer fid) {

        Page<Unit> page = new Page<>(current, pageSize);
        QueryWrapper<Unit> qw = new QueryWrapper<>();
        if (cid != null) {
            qw.eq(!StringUtils.isEmpty(unitCode), "unit_code", unitCode);
            qw.eq(fid != null, "fid", "fid");
            qw.eq("cid", cid);
        }
        IPage<Unit> p = unitService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取单元信息的接口")
    @ApiImplicitParam(name = "uid", value = "单元id", required = true)
    @GetMapping("/getUnit")
    public Response<Unit> getUnit(@RequestParam Integer uid) {
        Unit unit = unitService.getById(uid);
        return new Response<>(unit);
    }

    @ApiOperation("添加或者修改单元信息的接口")
    @PostMapping("/save")
    public Response saveUnit(Unit unit) {
        // uid为空为添加否则为更改
        if (unit.getUid() == null) {
            if (unit.getCid() == null || unit.getFid() == null || StringUtils.isEmpty(unit.getUnitCode())) {
                throw new ParamException(ErrorEnum.INCOMPLETE_PARAM);
            }
            Unit unit1 = unitService.getOne(new QueryWrapper<Unit>().eq("fid", unit.getFid()).eq("unit_code", unit.getUnitCode()));
            if (unit1 != null) {
                throw new ParamException("3", "单元编号已存在");
            }
            unit.setCreateTime(LocalDateTime.now());
        } else {
            if (!StringUtils.isEmpty(unit.getUnitCode())) {
                Unit unit1 = unitService.getOne(new QueryWrapper<Unit>().eq("fid", unit.getFid()).eq("unit_code", unit.getUnitCode()));
                if (unit1 != null) {
                    throw new ParamException("3", "单元编号已存在");
                }
            }
        }
        unit.setUpdateTime(LocalDateTime.now());
        if (!unitService.saveOrUpdate(unit)) {
            throw new CrudException("100", "添加或者更改失败");
        }
        return new Response();
    }

    @ApiOperation("删除单元信息的接口")
    @ApiImplicitParam(name = "uid", value = "单元id", required = true)
    @GetMapping("/delete")
    public Response deleteUnit(@RequestParam Integer uid) {
        if (!unitService.removeById(uid)) {
            throw new CrudException("103", uid + "删除失败");
        }
        return new Response();
    }
}
