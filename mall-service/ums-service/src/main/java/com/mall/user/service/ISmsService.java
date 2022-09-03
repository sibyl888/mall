package com.mall.user.service;

import com.mall.common.enums.SmsType;

import java.util.Map;


/**
 * @author sbn
 * @date 2022/8/22 17:48
 */
public interface ISmsService {
    /***
     * 发送短信
     * @param smsType 短信类型
     * @param mobile 手机号码
     * @param params 替换参数
     */
    boolean sendSms(SmsType smsType, String mobile, Map<String, String> params);

    /***
     * 校验验证码是否存在
     * @param mobile 手机号码
     * @param code 验证码
     * @return 是否存在
     */
    boolean checkMsg(String mobile, String code);
}
