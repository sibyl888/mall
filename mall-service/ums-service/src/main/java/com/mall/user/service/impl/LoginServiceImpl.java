package com.mall.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.mall.common.Result;
import com.mall.common.constants.RedisKeyConstants;
import com.mall.common.constants.UserConstants;
import com.mall.common.enums.ResultEnum;
import com.mall.common.utils.SnowflakeIdWorker;
import com.mall.user.db.dao.UserDao;
import com.mall.user.model.dto.TokenUser;
import com.mall.user.model.entity.UserEntity;
import com.mall.user.model.vo.TokenVO;
import com.mall.user.service.ILoginService;
import com.mall.user.model.req.LoginReq;
import com.mall.user.model.vo.UserVO;
import com.mall.user.service.ISmsService;
import com.mall.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author sbn
 * @date 2022/7/28 17:25
 */
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ISmsService smsService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private IUserService userService;

    @Override
    public Result<TokenVO> login(LoginReq loginReq) {
        if (!smsService.checkMsg(loginReq.getMobile(), loginReq.getCode())) {
            return Result.failed(ResultEnum.VALID_CODE_ERROR);
        }

        UserEntity user = userService.getUserByMobile(loginReq.getMobile());
        if (Objects.isNull(user)) {
            user = createUser(loginReq.getMobile());
            if (Objects.isNull(user)) {
                log.error("登录创建新用户失败，参数{}", loginReq);
                return Result.failed(ResultEnum.LOGIN_ERROR);
            }
        } else {
            if (!Objects.equals(user.getState(), 1)) {
                log.warn("用户禁止登录,{}", loginReq.getMobile());
                return Result.failed(ResultEnum.LOGIN_PROHIBIT);
            }
            UserEntity updateUser = new UserEntity();
            updateUser.setUserId(user.getUserId());
            updateUser.setLoginTime(LocalDateTime.now());
            userService.updateUserByUserId(updateUser);
        }
        TokenVO tokenVO = new TokenVO();
        String token = createToken(user);
        tokenVO.setToken(token);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        tokenVO.setUserVO(userVO);
        return Result.success(tokenVO);
    }

    /***
     * 创建新用户
     * @param mobile 手机号码
     * @return 返回用户对象
     */
    private UserEntity createUser(String mobile) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(SnowflakeIdWorker.getInstance().nextId());
        userEntity.setMobile(mobile);
        userEntity.setNickname("N" + userEntity.getUserId());
        userEntity.setAvatar(UserConstants.DEFAULT_AVATAR);
        userEntity.setLoginTime(LocalDateTime.now());
        userEntity.setState(1);
        boolean isSuc = userDao.insert(userEntity) > 0;
        if (isSuc) {
            return userEntity;
        }
        return null;
    }

    /***
     * 创建token
     * @param userEntity 当前用户数据
     * @return token
     */
    private String createToken(UserEntity userEntity) {
        TokenUser tokenUser = new TokenUser();
        BeanUtils.copyProperties(userEntity, tokenUser);
        String token = RandomStringUtils.randomAlphanumeric(32);
        String redisKey = RedisKeyConstants.LOGIN_TOKEN_PREFIX + userEntity.getMobile();
        String oldToken = redisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isNotBlank(oldToken)) {
            //如果有其他token,删除，维持最新的一个
            redisTemplate.delete(RedisKeyConstants.TOKEN_USER_PREFIX + oldToken);
        }
        redisTemplate.opsForValue().set(redisKey, token, UserConstants.TOKEN_TIMEOUT, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(RedisKeyConstants.TOKEN_USER_PREFIX + token,
                JSON.toJSONString(tokenUser), UserConstants.TOKEN_TIMEOUT, TimeUnit.DAYS);
        return token;
    }
}
