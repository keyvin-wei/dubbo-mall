package com.keyvin.mall.server.service.dubbo;

import com.keyvin.mall.api.service.UserService;
import com.keyvin.mall.common.entity.User;
import com.keyvin.mall.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author weiwh
 * @date 2019/10/25 11:57
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
}
