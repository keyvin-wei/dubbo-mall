package com.keyvin.mall.model.dao;

import com.keyvin.mall.model.entity.User;

/**
 * @author weiwh
 * @date 2019/10/25 11:43
 */
public interface UserDao {
    User findById(Integer id);
}
