package com.mall.product.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sbn
 * @date 2022/7/28 17:50
 */
@Data
public class SkuReq {
    @ApiModelProperty(name = "ååįžį ",example = "sku123456")
    private String skuCode;
}
