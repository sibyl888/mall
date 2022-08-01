package com.mall.user.model.req;

import lombok.Data;

/**
 * @author sbn
 * @date 2022/7/28 14:09
 */
@Data
public class LoginReq {

    private String phone;
    private String code;

}
