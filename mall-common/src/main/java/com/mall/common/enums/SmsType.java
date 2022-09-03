
package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 短信类型
 *
 * @author sbn
 * @date 2022/7/26 16:06
 */
@Getter
@AllArgsConstructor
public enum SmsType {

    /**
     * 发送验证码
     */
    VALID(1, "SMS_234123456", "感谢您对xxx的支持。您的验证码是${code}，请勿把验证码泄漏给第三方。"),

    ;

    /***
     * 短信类型
     */
    private Integer type;
    /***
     * 短信模板
     */
    private String templateCode;
    /***
     * 短信内容
     */
    private String content;

}
