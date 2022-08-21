package com.mall.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.common.Result;
import com.mall.common.enums.ResultEnum;
import com.mall.common.utils.SnowflakeIdWorker;
import com.mall.user.db.dao.UserDao;
import com.mall.user.model.entity.UserEntity;
import com.mall.user.service.ILoginService;
import com.mall.user.model.req.LoginReq;
import com.mall.user.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author sbn
 * @date 2022/7/28 17:25
 */
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public Result<UserVO> login(LoginReq loginReq) {
        //check code TODO
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getMobile, loginReq.getPhone());
        UserEntity user = userDao.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            user = createUser(loginReq.getPhone());
            if (Objects.isNull(user)) {
                log.error("登录创建新用户失败，参数{}", loginReq);
                return Result.failed(ResultEnum.LOGIN_ERROR);
            }
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return Result.success(userVO);
    }

    /***
     * 创建新用户
     * @param phone
     * @return
     */
    private UserEntity createUser(String phone) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(SnowflakeIdWorker.getInstance().nextId());
        userEntity.setMobile(phone);
        userEntity.setNickname("N" + userEntity.getUserId());
        userEntity.setAvatar("https://img0.baidu.com/it/u=3242477935,314283037&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
        userEntity.setLoginTime(LocalDateTime.now());
        userEntity.setState(1);
        userEntity.setVersion(1);
        userEntity.setCreateTime(LocalDateTime.now());
        userEntity.setLoginTime(LocalDateTime.now());
        boolean isSuc = userDao.insert(userEntity) > 0;
        if (isSuc) {
            return userEntity;
        }
        return null;
    }
}
