package com.wuye.manage.wuye.resident.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuye.manage.wuye.resident.entity.Resident;
import com.wuye.manage.wuye.resident.entity.ResidentUserVo;

/**
 * <p>
 * 住户表 服务类
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-11
 */
public interface IResidentService extends IService<Resident> {

    public IPage<ResidentUserVo> getResidentPageWithUser(Page<ResidentUserVo> page, QueryWrapper<ResidentUserVo> qw);
}
