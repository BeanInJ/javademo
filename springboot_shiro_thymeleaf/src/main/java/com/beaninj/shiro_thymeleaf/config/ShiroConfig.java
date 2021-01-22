package com.beaninj.shiro_thymeleaf.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.beaninj.shiro_thymeleaf.util.MyRealm;
import com.beaninj.shiro_thymeleaf.util.cache.RedisCacheManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getFilter(DefaultWebSecurityManager dwsManager) {
        // 负责拦截所有请求
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(dwsManager);

        // 配置公共资源和受限资源
        Map<String, String> map = new HashMap<String, String>();
        map.put("/**", "authc");                  // authc 表示请求这个资源需要认证和授权
        map.put("/user/**","anon");               // anon 这个资源不需要认证
        map.put("/register.html", "anon");
        map.put("/login.html", "anon");

        // 默认认证界面路径 (也就是不管你访问哪个界面，都会先跳到这个界面认证)
        shiroFilterFactoryBean.setLoginUrl("/user/loginhtml");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager getManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    @Bean
    public Realm getRealm() {
        MyRealm myRealm = new MyRealm();
        // 修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);

        myRealm.setCredentialsMatcher(credentialsMatcher);
        // 开启缓存
        myRealm.setCacheManager(new RedisCacheManager());
        // 全局都开启
        myRealm.setCachingEnabled(true);
        // 开启授权的缓存
        myRealm.setAuthorizationCachingEnabled(true);
        // 为授权缓存起一个名字（不起也可以，有默认的名字）
        myRealm.setAuthorizationCacheName("authorizationCache");
        // 开启认证的缓存
        myRealm.setAuthenticationCachingEnabled(true);
        // 为认证缓存起一个名字（不起也可以，有默认的名字）
        myRealm.setAuthenticationCacheName("authenticationCache");


        return myRealm;
    }
}
