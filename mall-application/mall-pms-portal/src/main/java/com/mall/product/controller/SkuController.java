package com.mall.product.controller;

import com.mall.common.PageResult;
import com.mall.common.Result;
import com.mall.config.log.LogAnnotation;
import com.mall.product.ISkuService;
import com.mall.product.model.req.SkuReq;
import com.mall.product.model.vo.SkuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author sbn
 * @date 2022/7/26 16:06
 */
@Api(value = "商品 控制器", tags = "商品 控制器")
@RestController
public class SkuController {

    @Autowired
    private ISkuService skuService;

    @ApiOperation(value = "商品列表")
    @PostMapping("/sku/list")
    @LogAnnotation(desc = "用户登录", whetherPrintReturnInfo = true)
    public Result<PageResult<SkuVO>> skuListByPage(@Validated @RequestBody SkuReq skuReq) {
        return skuService.skuListByPage(skuReq);
    }


}
