package com.mall.user.service.impl;

import com.mall.common.Result;
import com.mall.user.service.ILoginService;
import com.mall.user.model.req.LoginReq;
import com.mall.user.model.vo.UserVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author sbn
 * @date 2022/7/28 17:25
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Override
    public Result<UserVO> login(LoginReq loginReq) {
        UserVO userVO = new UserVO();
        userVO.setUserId(123456L);
        userVO.setMobile("13716310000");
        userVO.setNickname("心雨");
        userVO.setAvatar("http://abc.jpg");
        userVO.setState(1);
        userVO.setLoginTime(LocalDateTime.now());

        return Result.success(userVO);
    }
}
