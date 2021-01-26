package com.beaninj.springboot_jwt.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.beaninj.springboot_jwt.entity.User;
import com.beaninj.springboot_jwt.service.UserService;
import com.beaninj.springboot_jwt.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public Map<String,Object> login(User user){
        log.info("用户名：[{}]",user.getName());
        log.info("密码：[{}]",user.getPwd());

        Map<String,Object> map=new HashMap<>();
        try {
            User user1 = userService.login(user);
            Map<String,String> payload = new HashMap<>();
            payload.put("id",user1.getId()+"");
            payload.put("name",user1.getName());
            String token = JWTUtils.getToken(payload);

            map.put("state","true");
            map.put("msg","认证成功");
            map.put("token",token);
        } catch (Exception e) {
            map.put("state","false");
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @PostMapping("test")
    public Map<String,Object> tset(){
        // 这里面就不用验证token了，因为拦截器已经在这之前已经处理了
        Map<String,Object> map=new HashMap<>();
        map.put("state","true");
        map.put("msg","请求成功");
        return map;
    }
    @PostMapping("test1")
    public Map<String,Object> tset1(HttpServletRequest request){
        // 如果需要获取token里面的信息，这里也可以拿到
        String token = request.getHeader("token");
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        System.out.println(tokenInfo.getClaim("id").asString());
        System.out.println(tokenInfo.getClaim("name").asString());

        Map<String,Object> map=new HashMap<>();
        map.put("state","true");
        map.put("msg","请求成功");
        return map;
    }
}
