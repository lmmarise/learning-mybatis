package com.tsb.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 权限表(SysPrivilege)实体类
 *
 * @author makejava
 * @since 2020-04-12 19:11:06
 */
@Data
@ToString
public class SysPrivilege implements Serializable {
    private static final long serialVersionUID = -19352228149849134L;
    /**
    * 权限ID
    */
    private Long id;
    /**
    * 权限名称
    */
    private String privilegeName;
    /**
    * 权限URL
    */
    private String privilegeUrl;
}