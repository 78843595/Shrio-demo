package com.zhaozm.shirodemo.service.impl;

import com.zhaozm.shirodemo.mapper.UserMapper;
import com.zhaozm.shirodemo.pojo.User;
import com.zhaozm.shirodemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper ;
    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
