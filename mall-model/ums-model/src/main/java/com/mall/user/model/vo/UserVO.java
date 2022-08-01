package com.mall.user.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sbn
 * @date 2022/7/26 20:14
 */
@Data
public class UserVO {

    @ApiModelProperty(name = "用户ID")
    private String userId;
}
