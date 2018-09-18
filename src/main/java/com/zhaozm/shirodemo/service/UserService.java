package com.zhaozm.shirodemo.service;

import com.zhaozm.shirodemo.pojo.User;

public interface UserService {
    User findByUsername(String username);
}
