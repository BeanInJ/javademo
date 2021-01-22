package com.beaninj.shiro.controller;

import com.beaninj.shiro.entity.User;
import com.beaninj.shiro.service.UserService;
import com.beaninj.shiro.util.VerifyCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("getImg")
    public void getImg(HttpSession session, HttpServletResponse httpServletResponse) throws IOException {
        // 生成验证码
        String s = VerifyCodeUtils.generateVerifyCode(4);
        System.out.println("验证码已生成，正在存入session："+s);
        // 放入session
        session.setAttribute("code", s);
        // 生成图片
        ServletOutputStream os = httpServletResponse.getOutputStream();
        httpServletResponse.setContentType("img/png");
        VerifyCodeUtils.outputImage(220, 60, os, s);
    }

    // 处理身份认证
    @RequestMapping("login")
    public String login(String username, String password, String code, HttpSession session) {
        try {
            // 比较验证码
            String codeInSession = (String) session.getAttribute("code");
            System.out.println(codeInSession);
            if (codeInSession.equals(code)) {
                //获取主体对象
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(username, password));
                return "redirect:/index.jsp";
            }
        } catch (UnsupportedTokenException e) {
            e.printStackTrace();
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return "redirect:/login.jsp";
    }

    // 退出登录
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    // 用户注册
    @RequestMapping("register")
    public String register(User user) {
        System.out.println(user.toString());
        try {
            // 注册成功，返回登录界面
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            // 失败继续待在注册页面
            e.printStackTrace();
            System.out.println("注册失败");
            return "redirect:/register.jsp";
        }
    }
}
