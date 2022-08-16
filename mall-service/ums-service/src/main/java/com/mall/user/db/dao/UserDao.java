package com.mall.user.db.dao;

import com.mall.user.model.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sbn
 * @since 2022-08-11
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

}
