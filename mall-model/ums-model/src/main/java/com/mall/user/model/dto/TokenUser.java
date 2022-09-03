package com.mall.user.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * token对应redis存储的user
 * </p>
 *
 * @author sbn
 * @version V1.0
 * @date 2022-08-12
 */
@Data
@EqualsAndHashCode
@ApiModel(value = "token对应redis存储的user", description = "token对应redis存储的user")
public class TokenUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户唯一标识")
    private Long userId;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickname;


}
