package com.beaninj.springboot_jwt.service;

import com.beaninj.springboot_jwt.entity.User;

public interface UserService {
    User login(User user);
}
