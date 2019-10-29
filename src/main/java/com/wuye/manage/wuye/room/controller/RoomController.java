package com.wuye.manage.wuye.room.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.dto.Response;
import com.wuye.manage.wuye.enums.ErrorEnum;
import com.wuye.manage.wuye.exception.CrudException;
import com.wuye.manage.wuye.exception.ParamException;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.service.IRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * <p>
 * 房屋 前端控制器
 * </p>
 *
 * @author Leafqun
 * @since 2019-10-29
 */
@RestController
@RequestMapping("/api/{version}")
@Api(tags = "房屋管理相关接口")
public class RoomController {

    @Resource
    private IRoomService roomService;

    @ApiOperation("获取房屋列表分页的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页，默认为1"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小，默认为15"),
            @ApiImplicitParam(name = "roomCode", value = "楼栋编码，查询条件"),
            @ApiImplicitParam(name = "uid", value = "单元id，查询条件"),
            @ApiImplicitParam(name = "fid", value = "楼栋id，查询条件"),
            @ApiImplicitParam(name = "cid", value = "小区id，查询条件"),
            @ApiImplicitParam(name = "name", value = "业主名，查询条件")
    })
    @GetMapping("/rooms/page")
    public Response<IPage<Room>> getRoomList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            Integer roomCode, Integer uid, Integer fid, Integer cid, String name) {
        Page<Room> page = new Page<>(current, pageSize);
        QueryWrapper<Room> qw = new QueryWrapper<>();
        qw.eq(roomCode != null, "room_code", roomCode);
        qw.eq(uid != null, "uid", uid);
        qw.eq(fid != null, "fid", fid);
        qw.eq(cid != null, "cid", cid);
        qw.eq(!StringUtils.isEmpty(name), "name", name);
        IPage<Room> p = roomService.page(page, qw);
        return new Response<>(p);
    }

    @ApiOperation("获取房屋详情的接口")
    @ApiImplicitParam(name = "rid", value = "房屋id", required = true)
    @GetMapping("/rooms/{rid}")
    public Response<Room> getRoom(@PathVariable(value = "rid") Integer rid) {
        Room room = roomService.getById(rid);
        return new Response<>(room);
    }

    @ApiOperation("添加或者修改房屋详情的接口")
    @PostMapping("/rooms/save")
    public Response save(Room room) {
        // rid为空，添加否则为修改
        if (room.getRid() == null) {
            if (room.getCid() == null || room.getFid() == null || room.getUid() == null || StringUtils.isEmpty(room.getRoomCode())) {
                throw new ParamException(ErrorEnum.INCOMPLETE_PARAM);
            }
            Room room1 = roomService.getOne(new QueryWrapper<Room>().eq("room_code", room.getRoomCode()).eq("uid", room.getUid()).eq("fid", room.getFid()).eq("cid", room.getCid()));
            if (room1 != null) {
                throw new ParamException("103", "房屋编号已存在");
            }
            room.setCreateTime(LocalDateTime.now());
        } else {
            if (!StringUtils.isEmpty(room.getRoomCode())) {
                Room r = roomService.getById(room.getRid());
                Room room1 = roomService.getOne(new QueryWrapper<Room>().eq("room_code", room.getRoomCode()).eq("uid", r.getUid()).eq("fid", r.getFid()).eq("cid", r.getCid()));
                if (room1 != null) {
                    throw new ParamException("103", "房屋编号已存在");
                }
            }
        }
        room.setUpdateTime(LocalDateTime.now());
        if (!roomService.insertOrUpdate(room)) {
            throw new CrudException("100", "添加或者删除失败");
        }
        return new Response();
    }

    @ApiOperation("删除房屋详情的接口")
    @ApiImplicitParam(name = "rid", value = "房屋id", required = true)
    @DeleteMapping("/rooms/{rid}")
    public Response delete(@PathVariable Integer rid) {
        if (!roomService.delete(rid)) {
            throw new CrudException("103", rid + "删除失败");
        }
        return new Response();
    }

    @ApiOperation("批量删除房屋详情的接口")
    @ApiImplicitParam(name = "rid", value = "房屋id数组", required = true)
    @DeleteMapping("/rooms")
    public Response batchDelete(@RequestParam Integer[] rid) {
        if (!roomService.deleteByIds(Arrays.asList(rid))) {
            throw new CrudException("103", "批量删除失败");
        }
        return new Response();
    }
}
