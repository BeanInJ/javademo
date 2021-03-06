package com.beaninj.shiro_thymeleaf.dao;


import com.beaninj.shiro_thymeleaf.entity.Perms;
import com.beaninj.shiro_thymeleaf.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    void save(User user);

    User findByUsername(String username);

    // 根据用户名查询角色
    User findRolesByUserName(String username);
    List<Perms> findPermsByRoleId(int id);

}
