package com.tsb.mbg.type;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: 自定义类型转换处理器 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-18 10:31 </p>
 **/
public class EnabledTypeHandler implements TypeHandler<Enabled> {
    private final Map<Integer, Enabled> enabledMap = new HashMap<>();

    public EnabledTypeHandler() {
        for (Enabled enabled : Enabled.values()) {
            enabledMap.put(enabled.getValue(), enabled);
        }
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, Enabled enabled, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, enabled.getValue());
    }

    @Override
    public Enabled getResult(ResultSet rs, String s) throws SQLException {
        int value = rs.getInt(s);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(ResultSet rs, int i) throws SQLException {
        int value = rs.getInt(i);
        return enabledMap.get(value);
    }

    @Override
    public Enabled getResult(CallableStatement cs, int i) throws SQLException {
        int value = cs.getInt(i);
        return enabledMap.get(value);
    }
}
