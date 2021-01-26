package com.beaninj.springboot_jwt.interceptors;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.beaninj.springboot_jwt.util.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/*
 * jwt拦截器
 * 实现spring提供的拦截器接口
 **/
public class JWTInterceptors implements HandlerInterceptor {
    // 实现preHandle方法（预先拦截方法）
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 直接在http请求头中拿token
        String token = request.getHeader("token");
        // 用来传递 请求后 返回的消息
        Map<String,Object> map=new HashMap<>();
        // 验证令牌
        try {
            JWTUtils.getTokenInfo(token);
            return true;
        }catch (SignatureVerificationException e) {
            map.put("msg","签名无效");
        }catch (TokenExpiredException e) {
            map.put("msg","token过期");
        } catch (AlgorithmMismatchException e) {
            map.put("msg","token算法不一致");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("msg","其他异常");
        }
        map.put("state","false");
        // 将map转为json
        String json = new ObjectMapper().writeValueAsString(map);
        // 给前端响应转为json后的map
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
