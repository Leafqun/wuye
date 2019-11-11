package com.wuye.manage.wuye.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuye.manage.wuye.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Leafqun
 * @since 2019-11-08
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select a.* from t_user a join t_community_user b on b.user_id = a.user_id and b.cid = #{cid} ${ew.customSqlSegment}")
    public IPage<User> selectPageByParam(Page<User> page, @Param(Constants.WRAPPER) Wrapper wrapper,  @Param("cid") String cid);
}
