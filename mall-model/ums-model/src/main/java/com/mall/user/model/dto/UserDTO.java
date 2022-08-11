package com.mall.user.model.dto;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表 传输实体
 * </p>
 *
 * @author sbn
 * @version V1.0
 * @date 2022-08-12
 */
@Data
@EqualsAndHashCode
@ApiModel(value = "User传输实体", description = "用户表传输实体")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    private Long id;

    @ApiModelProperty(value = "用户唯一标识")
    private Long userId;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像地址")
    private String avatar;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "用户状态 1-正常 2-禁止")
    private Integer state;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
