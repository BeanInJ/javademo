package com.beaninj.shiro.util;

import com.beaninj.shiro.entity.Perms;
import com.beaninj.shiro.entity.User;
import com.beaninj.shiro.service.UserService;
import com.beaninj.shiro.util.cache.MyBySource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取身份信息
        String pp = (String) principalCollection.getPrimaryPrincipal();
        // 根据主身份信息获取角色 和 权限信息
        UserService us = (UserService) ApplicationContextUtils.getBean("userService");
        User user = us.findRolesByUserName(pp);
        // 授权角色
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                simpleAuthorizationInfo.addRole(role.getName());
                // 权限信息
                List<Perms> perms = us.findPermsByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(perms)){
                    perms.forEach(perm -> simpleAuthorizationInfo.addStringPermission(perm.getName()));
                }

            });
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        // 在工厂中获取service对象
        UserService us= (UserService) ApplicationContextUtils.getBean("userService");
        User user = us.findByUsername(principal);

        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new MyBySource(user.getSalt()),this.getName());
        }
        return null;
    }


}
