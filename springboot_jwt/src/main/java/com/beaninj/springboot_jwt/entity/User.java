package com.beaninj.springboot_jwt.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private int id;
    private String name;
    private String pwd;
}