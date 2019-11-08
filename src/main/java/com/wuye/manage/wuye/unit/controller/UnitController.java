package com.wuye.manage.wuye.unit.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.common.ExcelUtils;
import com.wuye.manage.wuye.config.ApiVersion;
import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.enums.ErrorEnum;
import com.wuye.manage.wuye.exception.CrudException;
import com.wuye.manage.wuye.exception.ParamException;
import com.wuye.manage.wuye.floor.service.IFloorService;
import com.wuye.manage.wuye.unit.entity.Unit;
import com.wuye.manage.wuye.unit.entity.UnitUserVo;
import com.wuye.manage.wuye.unit.service.IUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 单元表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-28
 */
@RestController
@RequestMapping("/api/{version}")
@Api(tags = "单元管理相关接口")
@ApiVersion(1)
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
            @ApiImplicitParam(name = "floorId", value = "楼栋id，查询条件")
    })
    @PostMapping("/units/page")
    public Response<IPage<UnitUserVo>> getUnitList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            String unitCode, @RequestParam Integer cid, Integer floorId) {

        Page<UnitUserVo> page = new Page<>(current, pageSize);
        QueryWrapper<UnitUserVo> qw = new QueryWrapper<>();

        qw.eq(!StringUtils.isEmpty(unitCode), "a.unit_code", unitCode);
        qw.eq(floorId != null, "a.floor_id", floorId);
        qw.eq("a.cid", cid);

        IPage<UnitUserVo> p = unitService.selectPageWithUser(page, qw, cid);
        return new Response<>(p);
    }

    @ApiOperation("获取单元信息的接口")
    @ApiImplicitParam(name = "unitId", value = "单元id", required = true)
    @PostMapping("/units/{unitId}")
    public Response<Unit> getUnit(@PathVariable Integer unitId) {
        Unit unit = unitService.getById(unitId);
        return new Response<>(unit);
    }

    @ApiOperation("添加或者修改单元信息的接口")
    @PostMapping("/units/save")
    public Response saveUnit(Unit unit) {
        // uid为空为添加否则为更改
        if (unit.getUnitId() == null) {
            if (unit.getCid() == null || unit.getFloorId() == null || StringUtils.isEmpty(unit.getUnitCode())) {
                throw new ParamException(ErrorEnum.INCOMPLETE_PARAM);
            }
            Unit unit1 = unitService.getOne(new QueryWrapper<Unit>().eq("floor_id", unit.getFloorId()).eq("unit_code", unit.getUnitCode()));
            if (unit1 != null) {
                throw new ParamException("3", "单元编号已存在");
            }
            unit.setCreateTime(LocalDateTime.now());
        } else {
            if (!StringUtils.isEmpty(unit.getUnitCode())) {
                Unit u = unitService.getById(unit.getUnitId());
                Unit unit1 = unitService.getOne(new QueryWrapper<Unit>().eq("cid", u.getCid()).eq("floor_id", u.getFloorId()).eq("unit_code", unit.getUnitCode()));
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
    @ApiImplicitParam(name = "unitId", value = "单元id", required = true)
    @PostMapping("/units/delete/{unitId}")
    public Response deleteUnit(@PathVariable Integer unitId) {
        if (!unitService.delete(unitId)) {
            throw new CrudException("103", unitId + "删除失败");
        }
        return new Response();
    }

    @ApiOperation("批量删除单元信息的接口")
    @ApiImplicitParam(name = "unitId", value = "单元id数组", required = true)
    @PostMapping("/units/delete")
    public Response batchDelete(@RequestParam Integer[] unitId) {
        if (!unitService.batchDelete(Arrays.asList(unitId))) {
            throw new CrudException("103", "批量删除失败");
        }
        return new Response();
    }

    @ApiOperation("批量添加单元的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cid", value = "小区id", required = true),
            @ApiImplicitParam(name = "fid", value = "楼栋id", required = true),
            @ApiImplicitParam(name = "mid", value = "管理员id"),
            @ApiImplicitParam(name = "file", value = "上传的Excel文件"),
    })
    @PostMapping("/units/batch-insert")
    public Response batchInsert(
            @RequestParam Integer cid,
            @RequestParam Integer floorId,
            @RequestParam MultipartFile file,
            Integer cmid) {
        List<Unit> unitList = ExcelUtils.importExcel(file, 0, 1, Unit.class);
        int i = 1;
        for (Unit unit : unitList) {
            if (!StringUtils.isEmpty(unit)) {
                throw new ParamException("3", "第" + i + "行数据单元编码不存在");
            }
            Unit u = unitService.getOne(new QueryWrapper<Unit>().eq("unit_code", unit.getUnitCode()).eq("floor_id", floorId).eq("cid", cid));
            if (u != null) {
                throw new ParamException("3", "第" + i + "行数据单元编码已存在");
            }
            unit.setCreateTime(LocalDateTime.now());
            unit.setUpdateTime(LocalDateTime.now());
            unit.setCid(cid);
            unit.setFloorId(floorId);
            unit.setUserId(cmid);
        }
        if (!unitService.saveBatch(unitList)) {
            throw new CrudException("105", "批量插入失败");
        }
        return new Response();
    }

    @ApiOperation("获取某一楼栋所有单元信息的接口")
    @ApiImplicitParam(name = "floorId", value = "楼栋id", required = true)
    @PostMapping("/floors/{floorId}/units/list")
    public Response<List<Unit>> getUnitListByFid(@PathVariable Integer floorId) {
        List<Unit> unitList = unitService.list(new QueryWrapper<Unit>().eq("floor_id", floorId));
        return new Response<>(unitList);
    }

}
