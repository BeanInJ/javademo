package com.baomidou.ant.sprinboot_gennerator.service.impl;

import com.baomidou.ant.sprinboot_gennerator.entity.User;
import com.baomidou.ant.sprinboot_gennerator.mapper.UserMapper;
import com.baomidou.ant.sprinboot_gennerator.service.IUserService;
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
