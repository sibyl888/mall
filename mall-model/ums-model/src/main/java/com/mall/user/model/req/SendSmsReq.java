package com.mall.user.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author sbn
 * @date 2022/8/22 17:44
 */
@Data
@ApiModel(value = "发送短信请求参数", description = "发送短信请求参数")
public class SendSmsReq {
    @ApiModelProperty(value = "手机号")
    @Pattern(regexp = "1[0-9]{10}", message = "请输入正确的手机号")
    private String mobile;
}
