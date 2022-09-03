package com.mall.user.service;


import com.mall.common.Result;
import com.mall.user.model.req.LoginReq;
import com.mall.user.model.vo.TokenVO;

/**
 * @author sbn
 * @date 2022/7/28 17:24
 */
public interface ILoginService {
    /***
     * 用户登录
     * @param loginReq 登录参数
     * @return 用户信息
     */
    Result<TokenVO> login(LoginReq loginReq);
}
