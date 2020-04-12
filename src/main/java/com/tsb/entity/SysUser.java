package com.tsb.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2020-04-11 21:53:43
 */
@Data
@ToString
public class SysUser implements Serializable {
    private static final long serialVersionUID = -64915717723059269L;
    /**
    * 用户ID
    */
    private Long id;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 密码
    */
    private String userPassword;
    /**
    * 邮箱
    */
    private String userEmail;
    /**
    * 简介
    */
    private String userInfo;
    /**
    * 头像
    */
    private byte[] headImg;
    /**
    * 创建时间
    */
    private Date createTime;
}