package com.mall.product.impl;

import com.mall.common.PageResult;
import com.mall.common.Result;
import com.mall.product.ISkuService;
import com.mall.product.model.req.SkuReq;
import com.mall.product.model.vo.SkuVO;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author sbn
 * @date 2022/7/28 17:37
 */
@Service
public class SkuServiceImpl implements ISkuService {
    @Override
    public Result<PageResult<SkuVO>> skuListByPage(SkuReq skuReq) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(1L);
        pageResult.setPageTotal(1L);
        pageResult.setTotal(1L);
        pageResult.setPageSize(10);
        SkuVO skuVO = new SkuVO();
        skuVO.setSkuCode("sku12345666");
        pageResult.setList(Arrays.asList(skuVO));

        return Result.success(pageResult);
    }
}
