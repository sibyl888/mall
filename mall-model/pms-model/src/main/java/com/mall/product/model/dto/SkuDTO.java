package com.mall.product.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sbn
 * @date 2022/7/28 17:44
 */
@Data
public class SkuDTO {
    @ApiModelProperty(name = "ååįžį ",example = "sku123456")
    private String skuCode;
}
