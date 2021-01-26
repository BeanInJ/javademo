package com.beaninj.springboot_jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    // 生成环境下这个SING是非常严格的，要保密，不能让别人知道
    private static final String SING = "xu!It'sASecret";

    /*
     * 生成token
     * @返回值 token
     **/
    public static String getToken(Map<String, String> map) {

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);                  // 过期时间7天

        // 创建builder
        JWTCreator.Builder builder = JWT.create();
        // 将数据遍历放进去      下面那个代码也可以写成这个样 map.forEach((k,v)->{builder.withClaim(k,v);});
        map.forEach(builder::withClaim);
        // 设置过期时间7天
        builder.withExpiresAt(instance.getTime());
        // 设置密钥,并返回
        return builder.sign(Algorithm.HMAC256(SING));
    }

    /*
     * 验证token
     **/
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /*
     * 获取token信息
     **/
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}
