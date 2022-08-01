package com.mall.user.impl;

import com.mall.common.Result;
import com.mall.user.ILoginService;
import com.mall.user.model.req.LoginReq;
import com.mall.user.model.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author sbn
 * @date 2022/7/28 17:25
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Override
    public Result<UserVO> login(LoginReq loginReq) {
        UserVO userVO = new UserVO();
        userVO.setUserId("user666");
        return Result.success(userVO);
    }
}
