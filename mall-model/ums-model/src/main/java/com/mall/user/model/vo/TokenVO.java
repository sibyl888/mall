package com.mall.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 登录返回的token 页面展示实体
 * </p>
 *
 * @author sbn
 * @version V1.0
 * @date 2022-08-12
 */
@Data
@EqualsAndHashCode
@ApiModel(value = "登录返回的token页面展示实体", description = "登录返回的token页面展示实体")
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "用户信息")
    private UserVO userVO;


}
