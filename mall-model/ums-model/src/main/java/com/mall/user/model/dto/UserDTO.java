package com.mall.user.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sbn
 * @date 2022/7/26 20:15
 */
@Data
public class UserDTO {

    @ApiModelProperty(name = "用户ID")
    private String userId;

}
