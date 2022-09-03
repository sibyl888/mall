package com.mall.user.controller;

import com.google.common.collect.Maps;
import com.mall.common.Result;
import com.mall.common.enums.ResultEnum;
import com.mall.common.enums.SmsType;
import com.mall.user.model.req.SendSmsReq;
import com.mall.user.service.ISmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sbn
 * @date 2022/7/26 16:06
 */
@Api(value = "短信 控制器", tags = "短信 控制器")
@RestController
public class SmsController {

    @Autowired
    private ISmsService smsService;

    /**
     * 发送验证码接口
     */
    @PostMapping("/send/sms")
    @ApiOperation(value = "发送验证码", notes = "用户发送验证码")
    public Result<Boolean> sendSms(@Validated @RequestBody SendSmsReq sendSmsReq) {
        boolean flag = smsService.sendSms(SmsType.VALID, sendSmsReq.getMobile(), Maps.newHashMap());
        if (flag) {
            return Result.success();
        }
        return Result.failed(ResultEnum.SEND_SMS_ERROR);
    }
}
