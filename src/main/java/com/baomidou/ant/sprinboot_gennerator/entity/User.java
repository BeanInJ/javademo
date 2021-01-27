package com.baomidou.ant.sprinboot_gennerator.entity;

import 你自己的父类实体,没有就不用设置!;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends 你自己的父类实体,没有就不用设置! {

    private static final long serialVersionUID = 1L;

    private String name;

    private String pwd;


}
