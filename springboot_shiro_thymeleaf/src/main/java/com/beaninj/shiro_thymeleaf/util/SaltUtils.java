package com.beaninj.shiro_thymeleaf.util;

import java.util.Random;

public class SaltUtils {
    public static String getSalt(int n) {
        char[] chars = "qazwsxedcrfvtgbyhnujmikolpQAZWSXEDCRFVTGBYHNUJMIKOLP0123456789,./;'[]-=".toCharArray();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char a = chars[new Random().nextInt(chars.length)];
            s.append(a);
        }
        return s.toString();
    }
}
