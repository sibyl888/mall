package com.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mall.user.db.dao.UserDao;
import com.mall.user.model.entity.UserEntity;
import com.mall.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sbn
 * @date 2022/9/3 10:05 下午
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserEntity getUserByUserId(Long userId) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUserId, userId);
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public UserEntity getUserByMobile(String mobile) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getMobile, mobile);
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public boolean updateUserByUserId(UserEntity updateUser) {
        LambdaUpdateWrapper<UserEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(UserEntity::getUserId, updateUser.getUserId());
        return userDao.update(updateUser, updateWrapper) > 0;
    }
}
