package com.beaninj.shiro_thymeleaf.service;

import com.beaninj.shiro_thymeleaf.dao.UserDao;
import com.beaninj.shiro_thymeleaf.entity.Perms;
import com.beaninj.shiro_thymeleaf.entity.User;
import com.beaninj.shiro_thymeleaf.util.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional  // 开启事务
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 密码md5+salt+hash散列
        System.out.println("-----------------注册：存入md5+salt+hash散列的密码---------------");
        String salt = SaltUtils.getSalt(6);
        Md5Hash newPassword = new Md5Hash(user.getPassword(), salt, 1024);

        user.setSalt(salt);
        user.setPassword(newPassword.toHex());

        userDao.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(int id) {
        return userDao.findPermsByRoleId(id);
    }


}
