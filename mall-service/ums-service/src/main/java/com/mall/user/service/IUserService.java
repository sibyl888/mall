package com.mall.user.service;

import com.mall.user.model.entity.UserEntity;

/**
 * @author sbn
 * @date 2022/9/3 10:04 下午
 */
public interface IUserService {
    /***
     * 根据userId获取用户数据
     * @param userId 用户唯一ID
     * @return 用户数据
     */
    UserEntity getUserByUserId(Long userId);

    /***
     * 根据手机号码获取用户数据
     * @param mobile 手机号码
     * @return 用户数据
     */
    UserEntity getUserByMobile(String mobile);

    /***
     * 根据userId更新用户数据
     * @param updateUser 待更新用户数据
     * @return 是否成功
     */
    boolean updateUserByUserId(UserEntity updateUser);
}
