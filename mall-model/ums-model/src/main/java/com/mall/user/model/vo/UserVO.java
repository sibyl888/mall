package com.mall.user.model.vo;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表 页面展示实体
 * </p>
 *
 * @author sbn
 * @version V1.0
 * @date 2022-08-12
 */
@Data
@EqualsAndHashCode
@ApiModel(value = "User页面展示实体", description = "用户表页面展示实体")
public class UserVO implements Serializable {

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "用户状态 1-正常 2-禁止")
    private Integer state;


}
