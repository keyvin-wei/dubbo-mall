package com.keyvin.mall.api.service;


import com.keyvin.mall.common.config.BaseResponse;

/**
 * @author weiwh
 * @date 2019/11/13 16:55
 */
public interface ItemService {
    public BaseResponse listItem();

    public BaseResponse listPageItem(Integer pageNo, Integer pageSize);

}
