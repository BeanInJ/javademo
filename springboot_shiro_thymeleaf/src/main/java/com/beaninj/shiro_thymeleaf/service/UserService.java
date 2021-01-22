package com.beaninj.shiro_thymeleaf.service;


import com.beaninj.shiro_thymeleaf.entity.Perms;
import com.beaninj.shiro_thymeleaf.entity.User;

import java.util.List;

public interface UserService {
    // 用户注册
    void register(User user);

    // 查询用户
    User findByUsername(String username);

    // 根据用户名查询角色
    User findRolesByUserName(String username);

    List<Perms> findPermsByRoleId(int id);
}
