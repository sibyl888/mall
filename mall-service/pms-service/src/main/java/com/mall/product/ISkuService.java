package com.mall.product;

import com.mall.common.PageResult;
import com.mall.common.Result;
import com.mall.product.model.req.SkuReq;
import com.mall.product.model.vo.SkuVO;

/**
 * @author sbn
 * @date 2022/7/28 17:37
 */
public interface ISkuService {
    /***
     * 分页查询商品
     * @param skuReq 参数
     * @return 商品列表
     */
    Result<PageResult<SkuVO>> skuListByPage(SkuReq skuReq);
}
