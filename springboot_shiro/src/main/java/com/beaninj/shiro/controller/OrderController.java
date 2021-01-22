package com.beaninj.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author BeanInJ
 * @Date 16:04 2021/1/14
 **/
@Controller
@RequestMapping("order")
public class OrderController {
    @RequestMapping("save")
//    @RequiresRoles("admin")  //判断角色
//    @RequiresRoles(value = {"admin","user"})  //判断角色  同时具有admin、user
    @RequiresPermissions("user:del:01")    // 判断权限字符串
    public String save() {
        // 代码方式授权
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            System.out.println("保存订单");
        } else {
            System.out.println("无权访问");
        }
        return "redirect:/index.jsp";
    }
}







