package com.keyvin.mall.server.service.impl;

import com.keyvin.mall.api.dto.DemoDTO;
import com.keyvin.mall.api.service.RpcDemoService;
import com.keyvin.mall.api.service.UserService;
import com.keyvin.mall.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author weiwh
 * @date 2019/10/25 20:20
 */
public class RpcDemoServiceImpl implements RpcDemoService {
    @Autowired
    private UserService userService;

    @Override
    public DemoDTO getDemoDto(Integer id) {
        System.out.println("RpcDemoServiceImpl,getDemoDto ");
        User user = userService.findById(1);
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setName(user.getName());
        return demoDTO;
    }
}
