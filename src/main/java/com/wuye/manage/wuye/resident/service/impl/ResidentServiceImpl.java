package com.wuye.manage.wuye.resident.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.resident.entity.Resident;
import com.wuye.manage.wuye.resident.entity.ResidentUserVo;
import com.wuye.manage.wuye.resident.mapper.ResidentMapper;
import com.wuye.manage.wuye.resident.service.IResidentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 住户表 服务实现类
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-11
 */
@Service
public class ResidentServiceImpl extends ServiceImpl<ResidentMapper, Resident> implements IResidentService {

    @Resource
    private ResidentMapper residentMapper;

    @Override
    public IPage<ResidentUserVo> getResidentPageWithUser(Page<ResidentUserVo> page, QueryWrapper<ResidentUserVo> qw) {
        return residentMapper.selectPageWithUser(page, qw);
    }
}
