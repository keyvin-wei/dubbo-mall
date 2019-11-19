package com.keyvin.mall.client.controller;

import com.alibaba.fastjson.JSON;
import com.keyvin.mall.api.service.ItemService;
import com.keyvin.mall.common.config.BaseResponse;
import com.keyvin.mall.common.config.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author weiwh
 * @date 2019/11/13 20:10
 */
@RestController
public class ClientController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    public BaseResponse list(Integer pageNo, Integer pageSize) {
        BaseResponse br = new BaseResponse(StatusCode.success);
        try {
            if (pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0) {
                pageNo = 1;
                pageSize = 5;
            }
            BaseResponse response = itemService.listPageItem(pageNo, pageSize);
            log.info("list:" + JSON.toJSONString(response));
            br.setData(response);

        } catch (Exception e) {
            e.printStackTrace();
            br = new BaseResponse(StatusCode.faild);
        }
        return br;
    }

}
