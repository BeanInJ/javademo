package com.beaninj.shiro_thymeleaf.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/*
 * @Author BeanInJ
 * @Date 17:35 2021/1/21
 *
 * 这个类可以在springboot启动成功后，拿到对应的Bean
 **/
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    // 根据bean的名字获取工厂中指定bean
    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}
