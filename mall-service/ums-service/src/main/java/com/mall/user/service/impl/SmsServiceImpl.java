package com.mall.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.mall.common.constants.RedisKeyConstants;
import com.mall.common.enums.SmsType;
import com.mall.user.config.SmsProperties;
import com.mall.user.service.ISmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author sbn
 * @date 2022/8/22 17:48
 */
@Slf4j
@Service
public class SmsServiceImpl implements ISmsService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private SmsProperties smsProperties;
    /***
     * 默认短信验证码
     */
    private final static String DEFAULT_SMS_CODE = "666666";

    @Override
    public boolean sendSms(SmsType smsType, String mobile, Map<String, String> params) {
        if (SmsType.VALID.equals(smsType)) {
            String code = DEFAULT_SMS_CODE;
            if (smsProperties.getSendFlag()) {
                code = RandomUtil.randomNumbers(6);
            }
            params.put("code", code);
            boolean flag = true;
            if (smsProperties.getSendFlag()) {
                flag = sendSmsMessage(mobile, smsType.getTemplateCode(), params);
            }
            if (flag) {
                //发送短信成功
                redisTemplate.opsForValue().set(RedisKeyConstants.PHONE_SMS_PREFIX + mobile, code, smsProperties.getExpiration(), TimeUnit.MINUTES);
                return true;
            }
        } else {
            sendSmsMessage(mobile, smsType.getTemplateCode(), params);
        }
        return false;
    }

    /***
     * 校验验证码是否存在
     * @param mobile 手机号码
     * @param code 验证码
     * @return 是否存在
     */
    @Override
    public boolean checkMsg(String mobile, String code) {
        if (!smsProperties.getSendFlag() && Objects.equals(DEFAULT_SMS_CODE, code)) {
            return true;
        }
        String redisKey = RedisKeyConstants.PHONE_SMS_PREFIX + mobile;
        String smsCode = redisTemplate.opsForValue().get(redisKey);
        if (Objects.isNull(smsCode)) {
            return false;
        }
        //短信验证码存在
        if (Objects.equals(smsCode, code)) {
            //短信验证码通过后删除缓存
            redisTemplate.delete(redisKey);
            return true;
        }
        return false;
    }


    /***
     * 调用阿里云短信服务发送短信
     * @param mobile 手机号码
     * @param templateCode 短信模板
     * @param params 替换参数
     * @return 是否发送成功
     */
    private boolean sendSmsMessage(String mobile, String templateCode, Map<String, String> params) {
        try {
            com.aliyun.dysmsapi20170525.Client client = createClient();
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(mobile)
                    .setSignName(smsProperties.getSignName())
                    .setTemplateCode(templateCode)
                    .setTemplateParam(JSON.toJSONString(params));

            com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
            try {
                log.info("发送短信参数,{}", JSON.toJSONString(sendSmsRequest));
                SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
                log.info("发送短信结果,{}", JSON.toJSONString(sendSmsResponse));

                if (Objects.nonNull(sendSmsResponse) && Objects.equals("OK", sendSmsResponse.getBody().getCode())) {
                    log.info("发送短信成功,{}", sendSmsRequest.getPhoneNumbers());
                    return true;
                }
            } catch (TeaException error) {
                log.error("发送短信异常，{}", error.getMessage());
            } catch (Exception ex) {
                TeaException error = new TeaException(ex.getMessage(), ex);
                log.error("发送短信异常，{}", error.getMessage());
            }
        } catch (Exception e) {
            log.error("发送短信异常，{}", e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用AK&SK初始化账号Clien
     *
     * @return Client
     * @throws Exception 异常
     */
    private com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 您的 AccessKey ID
                .setAccessKeyId(smsProperties.getAccessKeyId())
                // 您的 AccessKey Secret
                .setAccessKeySecret(smsProperties.getAccessKeySecret());
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }


}
