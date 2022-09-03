package com.mall.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * 短信相关配置
 *
 * @author sbn
 * @date 2022/8/31 11:31
 **/
@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {
    /***
     * key
     */
    private String accessKeyId;
    /***
     * 秘钥
     */
    private String accessKeySecret;
    /***
     * 签名
     */
    private String signName;
    /***
     * 是否真正发送验证码
     */
    private Boolean sendFlag;
    /***
     * 验证码过期时间，单位分钟
     */
    private Integer expiration;

}
