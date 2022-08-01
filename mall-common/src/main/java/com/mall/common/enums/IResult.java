package com.mall.common.enums;

/**
 * 错误码
 *
 * @author sbn
 * @date 2022/7/28 11:20
 */
public interface IResult {
    /**
     * 返回码
     *
     * @return
     */
    String getCode();

    /***
     * 返回信息
     * @return
     */
    String getMsg();
}
