package com.beaninj.springboot_jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
class SpringbootJwtApplicationTests {

    @Test
    void contextLoads() {
        // HashMap<String, Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,260);     // 20秒

        String token = JWT.create()
                // .withHeader(map)                           // Header
                .withClaim("userid", 12)        // payload
                .withClaim("username", "zhang")
                .withExpiresAt(instance.getTime())         // 设置过期时间
                .sign(Algorithm.HMAC256("123ABCD!"));// 密钥

        System.out.println(token);
    }
    @Test
    void test(){
        // 创建验证对象
        JWTVerifier jwt_v = JWT.require(Algorithm.HMAC256("123ABCD!")).build();
        DecodedJWT verify = jwt_v.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTE2MzA0NTYsInVzZXJpZCI6MTIsInVzZXJuYW1lIjoiemhhbmcifQ.cecno06RxLIgwf8FmzM4epDby9i8inYK1swe_Qq4_7U\n");
        System.out.println(verify.getClaim("userid").asInt());
        System.out.println(verify.getClaim("username").asString());
        // 打印过期时间
        System.out.println(verify.getExpiresAt());

    }
}
