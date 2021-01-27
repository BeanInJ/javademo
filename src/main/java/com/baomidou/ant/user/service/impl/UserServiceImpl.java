package com.baomidou.ant.user.service.impl;

import com.baomidou.ant.user.entity.User;
import com.baomidou.ant.user.mapper.UserMapper;
import com.baomidou.ant.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}