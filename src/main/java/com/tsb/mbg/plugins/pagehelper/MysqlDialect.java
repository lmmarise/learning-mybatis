package com.tsb.mbg.plugins.pagehelper;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: MySQL方言的实现 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-19 17:47 </p>
 **/
@SuppressWarnings("rawtypes")
public class MysqlDialect implements Dialect {
    @Override
    public boolean skip(String id, Object parameterObject, RowBounds rowBounds) {
        // 这里使用RowBounds分页
        // 没有RowBounds参数时, 使用RowBounds.DEFAULT作为默认值
        return rowBounds == RowBounds.DEFAULT;
    }

    @Override
    public boolean beforeCount(String msId, Object parameterObject, RowBounds rowBounds) {
        // 只有使用PageRowBounds才能记录总数，否则查询了总数也没用
        return rowBounds instanceof PageRowBounds;
    }

    @Override
    public String getCountSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey countKey) {
        // 简单欲套实现MySQL count查询
        return "select count(*) from (" + boundSql.getSql() + ") temp";
    }

    @Override
    public void afterCount(Long count, Object parameterObject, RowBounds rowBounds) {
        // 记录总数，按照beforeCount逻辑，只有PageRowBounds才会查询count, 所以这里直接强制转换
        ((PageRowBounds) rowBounds).setTotal(count);
    }

    @Override
    public Object afterPage(List<?> pageList, Object parameterObject, RowBounds rowBounds) {
        // 直接返回查询结果
        return pageList;
    }

    @Override
    public boolean beforePage(String id, Object parameterObject, RowBounds rowBounds) {
        return rowBounds != RowBounds.DEFAULT;
    }

    @Override
    public String getPageSql(BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey pageKey) {
        //pageKey会影响缓存，通过固定的RowBounds可以保证二级缓存有效
        pageKey.update("RowBounds");
        return boundSql.getSql() + " limit " + rowBounds.getOffset() + "," + rowBounds.getLimit();
    }

    @Override
    public void setProperties(Properties properties) {
        // 没有其它参数
    }
}
