package com.wuye.manage.wuye.room.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.dto.Response;
import com.wuye.manage.enums.ErrorEnum;
import com.wuye.manage.exception.CrudException;
import com.wuye.manage.exception.ParamException;
import com.wuye.manage.wuye.room.entity.Room;
import com.wuye.manage.wuye.room.entity.RoomQueryVo;
import com.wuye.manage.wuye.room.service.IRoomService;
import io.swagger.annotations.*;
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
            @ApiImplicitParam(name = "unitId", value = "单元id，查询条件"),
            @ApiImplicitParam(name = "floorId", value = "楼栋id，查询条件"),
            @ApiImplicitParam(name = "cid", value = "小区id，查询条件"),
            @ApiImplicitParam(name = "name", value = "业主名，查询条件")
    })
    @PostMapping("/rooms/page")
    public Response<IPage<RoomQueryVo>> getRoomList(
            @RequestParam(required = false, defaultValue = "1") Integer current,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            Integer roomCode, Integer unitId, Integer floorId, @RequestParam Integer cid, String name) {
        Page<RoomQueryVo> page = new Page<>(current, pageSize);
        QueryWrapper<RoomQueryVo> qw = new QueryWrapper<>();
        qw.eq(roomCode != null, "a.room_code", roomCode);
        qw.eq(unitId != null, "a.unit_id", unitId);
        qw.eq(floorId != null, "a.floor_id", floorId);
        qw.eq("a.cid", cid);
        qw.eq(!StringUtils.isEmpty(name), "d.name", name);
        IPage<RoomQueryVo> p = roomService.getQueryPage(page, qw, cid);
        return new Response<>(p);
    }

    @ApiOperation("获取房屋详情的接口")
    @ApiImplicitParam(name = "roomId", value = "房屋id", required = true)
    @PostMapping("/rooms/{roomId}")
    public Response<Room> getRoom(@PathVariable(value = "roomId") Integer roomId) {
        Room room = roomService.getById(roomId);
        return new Response<>(room);
    }

    @ApiOperation("添加或者修改房屋详情的接口")
    @PostMapping("/rooms/save")
    public Response save(Room room) {
        // rid为空，添加否则为修改
        if (room.getRoomId() == null) {
            if (room.getCid() == null || room.getFloorId() == null || room.getUnitId() == null || StringUtils.isEmpty(room.getRoomCode())) {
                throw new ParamException(ErrorEnum.INCOMPLETE_PARAM);
            }
            Room room1 = roomService.getOne(new QueryWrapper<Room>().eq("room_code", room.getRoomCode()).eq("unit_id", room.getUnitId()).eq("floor_id", room.getFloorId()).eq("cid", room.getCid()));
            if (room1 != null) {
                throw new ParamException("103", "房屋编号已存在");
            }
            room.setCreateTime(LocalDateTime.now());
        } else {
            if (!StringUtils.isEmpty(room.getRoomCode())) {
                Room r = roomService.getById(room.getRoomId());
                Room room1 = roomService.getOne(new QueryWrapper<Room>().eq("room_code", room.getRoomCode()).eq("unit_id", r.getUnitId()).eq("floor_id", r.getFloorId()).eq("cid", r.getCid()));
                if (room1 != null) {
                    throw new ParamException("103", "房屋编号已存在");
                }
            }
        }
        room.setUpdateTime(LocalDateTime.now());
        if (!roomService.saveOrUpdate(room)) {
            throw new CrudException("100", "添加或者删除失败");
        }
        return new Response();
    }

    @ApiOperation("删除房屋详情的接口")
    @ApiImplicitParam(name = "roomId", value = "房屋id", required = true)
    @PostMapping("/rooms/delete/{roomId}")
    public Response delete(@PathVariable Integer roomId) {
        if (!roomService.removeById(roomId)) {
            throw new CrudException("103", roomId + "删除失败");
        }
        return new Response();
    }

    @ApiOperation("批量删除房屋详情的接口")
    @ApiImplicitParam(name = "roomId", value = "房屋id数组", required = true)
    @PostMapping("/rooms/delete")
    public Response batchDelete(@RequestParam Integer[] roomId) {
        if (!roomService.removeByIds(Arrays.asList(roomId))) {
            throw new CrudException("103", "批量删除失败");
        }
        return new Response();
    }
}
