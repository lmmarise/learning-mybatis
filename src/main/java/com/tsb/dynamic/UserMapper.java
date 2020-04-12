package com.tsb.dynamic;

import com.tsb.entity.SysUser;

import java.util.List;

/**
 * @program: new-mybatis
 * @description:
 * @author: Arise Tang
 * @create: 2020-04-12 10:57
 **/
public interface UserMapper {
    List<SysUser> selectAll();
}
