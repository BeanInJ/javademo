package com.beaninj.springboot_jwt.service;

import com.beaninj.springboot_jwt.dao.UserDao;
import com.beaninj.springboot_jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        User user1 = userDao.login(user);
        if(user1!=null) return user1;
        throw new RuntimeException("登录失败");
    }
}
