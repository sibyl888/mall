package com.mall.common;

import com.mall.common.enums.IResult;
import com.mall.common.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author sbn
 * @date 2022/7/26 16:06
 */
@Data
@ApiModel(value = "通用返回结构", description = "通用返回实体")
public class Result<T> implements Serializable {
    /**
     * 返回码的值
     */
    @ApiModelProperty(value = "返回码的值")
    private String code = ResultEnum.SUCCESS.getCode();
    /**
     * 返回码的描述
     */
    @ApiModelProperty(value = "返回码的描述")
    private String msg = ResultEnum.SUCCESS.getMsg();
    /**
     * 返回的数据
     */
    @ApiModelProperty(value = "返回的数据")
    private T data;

    @ApiModelProperty(value = "耗时")
    private Long timeCost;

    @ApiModelProperty(value = "数量")
    private Integer count = 0;

    public static <T> Result<T> success() {
        return restResult(null, String.valueOf(ResultEnum.SUCCESS.getCode()), ResultEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> success(T data) {
        return restResult(data, String.valueOf(ResultEnum.SUCCESS.getCode()), ResultEnum.SUCCESS.getMsg());
    }

    public static <T> Result<T> success(T data, String msg) {
        return restResult(data, String.valueOf(ResultEnum.SUCCESS.getCode()), msg);
    }

    public static <T> Result<T> failed() {
        return restResult(null, String.valueOf(ResultEnum.ERROR.getCode()), ResultEnum.ERROR.getMsg());
    }

    public static <T> Result<T> failed(String msg) {
        return restResult(null, String.valueOf(ResultEnum.ERROR.getCode()), msg);
    }

    public static <T> Result<T> failed(T data) {
        return restResult(data, String.valueOf(ResultEnum.ERROR.getCode()), ResultEnum.ERROR.getMsg());
    }

    public static <T> Result<T> failed(String code, String msg) {
        return restResult(null, code, msg);
    }

    public static <T> Result failed(IResult error) {
        return restResult(null, String.valueOf(error.getCode()), error.getMsg());
    }

    private static <T> Result<T> restResult(T data, String code, String msg) {
        Result<T> apiResult = new Result<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean isOk() {
        if (Objects.equals(ResultEnum.SUCCESS.getCode(), this.getCode())) {
            return true;
        }
        return false;
    }
}
