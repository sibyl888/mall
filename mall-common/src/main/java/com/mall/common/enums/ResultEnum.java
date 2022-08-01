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
    ERROR("500", "服务器异常");

    private final String code;
    private final String msg;

}
