package com.keyvin.mall.server.controller;

import com.keyvin.mall.common.redis.RedisService;
import com.keyvin.mall.model.entity.User;
import com.keyvin.mall.server.service.dubbo.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiwh
 * @date 2019/10/21 19:39
 */
@RestController
public class DemoController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/demo")
    public String index(){
        redisService.set("keyvin", "wwh");
        System.out.println("redis get:"+redisService.get("keyvin"));
        User user = userService.findById(1);
        return "hello ServerController index"+user.getName();
    }
}
