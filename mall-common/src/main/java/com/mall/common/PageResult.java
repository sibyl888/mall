package com.mall.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author sbn
 * @date 2022/7/26 16:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "分页返回实体", description = "分页返回实体")
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "当前页码")
    private long pageNum;

    @ApiModelProperty(name = "总页数")
    private long pageTotal;

    @ApiModelProperty(name = "总数")
    private long total;

    @ApiModelProperty(name = "查询分页长度")
    private int pageSize;

    @ApiModelProperty(name = "查询结果")
    private List<T> list;
}

