package com.keyvin.mall.client.dao;

import com.keyvin.mall.common.entity.User;

/**
 * @author weiwh
 * @date 2019/10/25 11:43
 */
public interface UserDao {
    User findById(Integer id);
}
