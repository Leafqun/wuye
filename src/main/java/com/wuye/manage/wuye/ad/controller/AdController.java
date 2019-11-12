package com.wuye.manage.wuye.ad.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.ad.entity.Ad;
import com.wuye.manage.wuye.ad.service.IAdService;
import com.wuye.manage.config.ApiVersion;
import com.wuye.manage.dto.Response;
import com.wuye.manage.exception.CrudException;
import com.wuye.manage.exception.ParamException;
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
 * 广告表 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@RestController
@RequestMapping("/api/{version}")
@Api(tags = "广告管理相关接口")
@ApiVersion(1)
public class AdController {

    @Resource
    private IAdService adService;

    @ApiOperation("获取用户分页信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "cid", value = "小区id", required = true),
            @ApiImplicitParam(name = "adName", value = "广告名称，查询条件"),
            @ApiImplicitParam(name = "sort", value = "广告分类，查询条件"),
            @ApiImplicitParam(name = "type", value = "广告类型，查询条件"),
            @ApiImplicitParam(name = "putonTime", value = "投放时间，查询条件"),
            @ApiImplicitParam(name = "auditStatus", value = "审核状态，查询条件"),
            @ApiImplicitParam(name = "playStatus", value = "播放状态，查询条件")
    })
    @PostMapping("/communities/{cid}/ads/page")
    public Response<IPage<Ad>> getAdList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @PathVariable Integer cid, String adName, String sort,
            String type, LocalDateTime putonTime, String auditStatus, String playStatus) {
        Page<Ad> page = new Page<>(current, pageSize);
        QueryWrapper<Ad> qw = new QueryWrapper<>();
        qw.eq("cid", cid);
        qw.eq(!StringUtils.isEmpty(adName), "ad_name", adName);
        qw.eq(!StringUtils.isEmpty(sort), "sort", sort);
        qw.eq(!StringUtils.isEmpty(type), "type", type);
        qw.eq(putonTime != null, "puton_time", putonTime);
        qw.eq(!StringUtils.isEmpty(auditStatus), "audit_status", auditStatus);
        qw.eq(!StringUtils.isEmpty(playStatus), "play_status", playStatus);
        qw.orderByDesc("create_time");
        IPage<Ad> p = adService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取公告详情的接口")
    @ApiImplicitParam(name = "adId", value = "广告id", required = true)
    @PostMapping("/ads/{adId}")
    public Response<Ad> getAd(@PathVariable Integer adId) {
        Ad record = adService.getById(adId);
        return new Response<>(record);
    }

    @ApiOperation("保存公告详情的接口")
    @PostMapping("/ads/save")
    public Response saveAd(Ad ad) {
        if (ad.getAdId() == null) {
            if (ad.getCid() == null) {
                throw new ParamException("103", "请求参数无小区id");
            }
            ad.setCreateTime(LocalDateTime.now());
        }
        ad.setCreateTime(LocalDateTime.now());
        if (!adService.saveOrUpdate(ad)) {
            throw new CrudException("103", "添加或者更改失败");
        }
        return new Response<>();
    }

    @ApiOperation("删除公告详情的接口")
    @ApiImplicitParam(name = "adId", value = "广告id", required = true)
    @PostMapping("/ads/delete/{adId}")
    public Response deleteRecordAd(@PathVariable Integer adId) {
        if (!adService.removeById(adId)) {
            throw new CrudException("103", "删除失败");
        }
        return new Response<>();
    }
}
