package com.tsb.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2020-04-11 22:03:05
 */
@Data
@ToString
public class SysRole implements Serializable {
    private static final long serialVersionUID = 171293737510961611L;
    /**
    * 角色ID
    */
    private Long id;
    /**
    * 角色名
    */
    private String roleName;
    /**
    * 有效标志
    */
    private Integer enabled;
    /**
    * 创建人
    */
    private Long createBy;
    /**
    * 创建时间
    */
    private Date createTime;
    /*
     * 用户信息
     * */
    private SysUser user;
}