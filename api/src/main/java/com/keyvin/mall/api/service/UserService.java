package com.keyvin.mall.api.service;

import com.keyvin.mall.common.entity.User;

/**
 * @author weiwh
 * @date 2019/10/25 11:56
 */
public interface UserService {
    User findById(Integer id);
}
