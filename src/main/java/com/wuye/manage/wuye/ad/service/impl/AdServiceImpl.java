package com.wuye.manage.wuye.ad.service.impl;

import com.wuye.manage.wuye.ad.entity.Ad;
import com.wuye.manage.wuye.ad.mapper.AdMapper;
import com.wuye.manage.wuye.ad.service.IAdService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 广告表 服务实现类
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-12
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements IAdService {

}
