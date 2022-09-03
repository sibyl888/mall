package com.mall.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * 登录请求参数
 *
 * @author sbn
 * @date 2022/7/28 14:09
 */
@Data
@ApiModel(value = "登录请求参数", description = "登录请求参数")
public class LoginReq {
    @ApiModelProperty(value = "手机号码")
    @Pattern(regexp = "1\\d{10}", message = "手机号码格式不正确")
    private String phone;

    @ApiModelProperty(value = "验证码")
    @Pattern(regexp = "[0-9]{6}", message = "验证码格式不正确")
    private String code;

}
