package com.beaninj.springboot_jwt.dao;

import com.beaninj.springboot_jwt.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User login(User user);
}
