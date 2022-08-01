package com.mall.product.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sbn
 * @date 2022/7/28 17:44
 */
@Data
public class SkuVO {
    @ApiModelProperty(name = "商品编码", example = "sku123456")
    private String skuCode;
}
