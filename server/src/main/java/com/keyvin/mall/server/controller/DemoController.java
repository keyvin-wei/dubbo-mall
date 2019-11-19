package com.keyvin.mall.server.controller;

import com.keyvin.mall.api.service.ItemService;
import com.keyvin.mall.api.service.UserService;
import com.keyvin.mall.common.config.BaseResponse;
import com.keyvin.mall.common.config.StatusCode;
import com.keyvin.mall.common.entity.User;
import com.keyvin.mall.common.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiwh
 * @date 2019/10/21 19:39
 */
@RestController
public class DemoController {
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ItemService itemService;

    @RequestMapping("/demo")
    public BaseResponse index(){
        BaseResponse br = new BaseResponse(StatusCode.success);
        try {
            redisService.set("keyvin", "wwh");
            log.info("redis get:"+redisService.get("keyvin"));
            User user = userService.findById(1);
            String str = "hello ServerController index"+user.getName();
            br.setData(str);
        }catch (Exception e){
            e.printStackTrace();
            br = new BaseResponse(StatusCode.faild);
        }
        return br;
    }

    @RequestMapping("/list")
    public BaseResponse list(Integer pageNo, Integer pageSize){
        BaseResponse br = new BaseResponse(StatusCode.success);
        try {
            if(pageNo==null||pageSize==null||pageNo<0||pageSize<0){
                pageNo=1;
                pageSize=10;
            }
            BaseResponse response = itemService.listPageItem(pageNo, pageSize);
            log.info("list:"+response);
            br.setData(response);

        }catch (Exception e){
            e.printStackTrace();
            br = new BaseResponse(StatusCode.faild);
        }
        return br;
    }

}
