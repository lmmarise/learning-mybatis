package com.tsb.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @program: new-mybatis
 * @description:
 * @author: Arise Tang
 * @create: 2020-04-12 19:14
 **/
public class PrivilegeProvider {
    public String selectById(final Long id) {
        return "select id, privilege_name, privilege_url " +
                "from sys_privilege where id = #{id}";
    }
}
