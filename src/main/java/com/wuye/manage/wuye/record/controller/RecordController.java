package com.wuye.manage.wuye.record.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.config.ApiVersion;
import com.wuye.manage.dto.Response;
import com.wuye.manage.exception.CrudException;
import com.wuye.manage.exception.ParamException;
import com.wuye.manage.wuye.record.entity.Record;
import com.wuye.manage.wuye.record.service.IRecordService;
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
 * 公告表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/api/{version}")
@Api(tags = "公告管理相关接口")
@ApiVersion(1)
public class RecordController {

    @Resource
    private IRecordService recordService;

    @ApiOperation("获取用户分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "cid", value = "小区id", required = true),
            @ApiImplicitParam(name = "title", value = "公共标题，查询条件"),
            @ApiImplicitParam(name = "type", value = "公告类型，查询条件")
    })
    @PostMapping("/communities/{cid}/records/page")
    public Response<IPage<Record>> getRecordList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @PathVariable Integer cid, String title, String type) {
        Page<Record> page = new Page<>(current, pageSize);
        QueryWrapper<Record> qw = new QueryWrapper<>();
        qw.eq("cid", cid);
        qw.eq(!StringUtils.isEmpty(title), "title", title);
        qw.eq(!StringUtils.isEmpty(type), "type", type);
        qw.orderByDesc("start_time");
        IPage<Record> p = recordService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取公告详情的接口")
    @ApiImplicitParam(name = "recordId", value = "公告id", required = true)
    @PostMapping("/records/{recordId}")
    public Response<Record> getRecord(@PathVariable Integer recordId) {
        Record record = recordService.getById(recordId);
        return new Response<>(record);
    }

    @ApiOperation("保存公告详情的接口")
    @PostMapping("/records/save")
    public Response saveRecord(Record record) {
        if (record.getRecordId() == null) {
            if (record.getCid() == null) {
                throw new ParamException("103", "请求参数无小区id");
            }
            record.setCreateTime(LocalDateTime.now());
        }
        record.setCreateTime(LocalDateTime.now());
        if (!recordService.saveOrUpdate(record)) {
            throw new CrudException("103", "添加或者更改失败");
        }
        return new Response<>();
    }

    @ApiOperation("删除公告详情的接口")
    @ApiImplicitParam(name = "recordId", value = "公告id", required = true)
    @PostMapping("/records/delete/{recordId}")
    public Response deleteRecord(@PathVariable Integer recordId) {
        if (!recordService.removeById(recordId)) {
            throw new CrudException("103", "删除失败");
        }
        return new Response<>();
    }
}
