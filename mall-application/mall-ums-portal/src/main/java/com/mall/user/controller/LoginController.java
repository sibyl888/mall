package com.mall.user.controller;

import com.mall.common.Result;
import com.mall.common.config.log.LogAnnotation;
import com.mall.user.model.req.LoginReq;
import com.mall.user.model.vo.UserVO;
import com.mall.user.service.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @author sbn
 * @date 2022/7/26 16:06
 */
@Api(value = "登录 控制器", tags = "登录 控制器")
@RestController
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    @LogAnnotation(desc = "用户登录", whetherPrintReturnInfo = true)
    public Result<UserVO> login(@Validated @RequestBody LoginReq loginReq) {
        return loginService.login(loginReq);
    }


}
