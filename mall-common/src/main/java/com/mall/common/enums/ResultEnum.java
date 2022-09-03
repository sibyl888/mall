package com.mall.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 通用错误码
 *
 * @author sbn
 * @date 2022/7/26 16:06
 */
@Getter
@AllArgsConstructor
public enum ResultEnum implements IResult {
    SUCCESS("200", "成功"),
    ERROR("500", "服务器异常"),
    /**
     * 参数部分
     ****/
    PARAM_ERROR("1001", "参数错误%s"),

    LOGIN_ERROR("1100", "登录失败"),
    SEND_SMS_ERROR("1101", "发送短信失败"),
    VALID_CODE_ERROR("1102", "验证码错误"),
    LOGIN_PROHIBIT("1103", "禁止登录"),


    ;

    private final String code;
    private final String msg;

}
