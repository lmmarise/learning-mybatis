package com;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.tsb.mbg.dao.SysRoleMapper;
import com.tsb.mbg.dao.SysUserMapper;
import com.tsb.mbg.entity.SysRole;
import com.tsb.mbg.entity.SysUser;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.junit.Test;

import java.sql.*;
import java.util.List;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: 缓存测试类 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-18 17:06 </p>
 **/
public class CacheTest extends BaseMapperTest {
    @Test
    public void l1Cache() {
        SysUser user1 = null;
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            user1 = sysUserMapper.selectById(1L);
            user1.setUserName("new name");
            SysUser user2 = sysUserMapper.selectById(1L);
            System.out.println("user1 name:" + user1.getUserName());
            System.out.println("user2 name:" + user2.getUserName());
            System.out.println("user1 == user2:" + (user1 == user2));
        }
        System.out.println("开启新的sqlSession");
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser user3 = sysUserMapper.selectById(1L);
            user1.setUserName("new2 name");
            SysUser user4 = sysUserMapper.selectById(1L);
            System.out.println("user1 == user3:" + (user1 == user3));
            System.out.println("user3 == user4:" + (user3 == user4));
            System.out.println("user1 == user4:" + (user1 == user4));
        }
    }

    @Test
    public void l2CacheTest() {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole role1 = roleMapper.selectRoleById(1L);
            role1.setRoleName("new name");
            SysRole role2 = roleMapper.selectRoleById(1L);
            System.out.println("role2 name= " + role2.getRoleName());
            System.out.println("role1 == role2 = " + (role1 == role2));
        }
        System.out.println("开启新的sqlSession");

        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole role1 = roleMapper.selectRoleById(1L);
            role1.setRoleName("new name");
            SysRole role2 = roleMapper.selectRoleById(1L);
            System.out.println("role2 name= " + role2.getRoleName());
            System.out.println("role1 == role2 = " + (role1 == role2));
        }
    }


    @Test
    public void dirtyDataTest() {
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = userMapper.selectUserAndRoleById9(1004L);
            System.out.println("用户名:" + sysUser.getUserName());
            System.out.println("        角色名:");
            for (SysRole sysRole : sysUser.getRoleList()) {
                System.out.println("        " + sysRole.getRoleName());
            }
        }
        System.out.println();
        System.out.println();
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole sysRole = roleMapper.selectRoleById(3L);
            sysRole.setRoleName("脏数据");
            roleMapper.updateByPrimaryKey(sysRole);
            sqlSession.commit();
        }
        System.out.println();
        System.out.println();
        try (SqlSession sqlSession = getSqlSessionFactory().openSession()) {
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysUser sysUser = userMapper.selectUserAndRoleById9(1004L);
            SysRole sysRole = roleMapper.selectRoleById(3L);
            System.out.println("用户名:" + sysUser.getUserName());
            System.out.println("    角色名:");
            for (SysRole role : sysUser.getRoleList()) {
                System.out.println("        " + role.getRoleName());
            }
            // 还原数据
            sysRole.setRoleName("蔡徐坤会员");
            roleMapper.updateByPrimaryKey(sysRole);
            sqlSession.commit();
        }

    }
}

interface Executor1 {

    ResultHandler NO_RESULT_HANDLER = null;

    // 会在所有的INSERT 、UPDATE 、DELETE 执行时被调用。
    //
    //```java
    //@Signature (
    //	type=Executor.class,
    //	method="update",
    //	args={MappedStatement.class, Object.class})
    //```
    int update(MappedStatement ms, Object parameter) throws SQLException;

    // 由于MyBatis 的设计原因，这个参数多的接口不能被拦截。
    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey cacheKey, BoundSql boundSql) throws SQLException;

    // 会在所有SELECT 查询方法执行时被调用。
    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;

    // 只有在查询的返回值类型为Cursor 时被调用。
    <E> Cursor<E> queryCursor(MappedStatement ms, Object parameter, RowBounds rowBounds) throws SQLException;

    // 只在通过SqlSession方法调用flushStatements方法或执行的接口方法中带有＠Flush注解时才被调用。
    List<BatchResult> flushStatements() throws SQLException;

    // 只在通过SqlSession方法调用commit方法时才被调用。
    void commit(boolean required) throws SQLException;

    // 只在通过SqlSession方法调用rollback方法时才被调用
    void rollback(boolean required) throws SQLException;

    CacheKey createCacheKey(MappedStatement ms, Object parameterObject, RowBounds rowBounds, BoundSql boundSql);

    boolean isCached(MappedStatement ms, CacheKey key);

    void clearLocalCache();

    void deferLoad(MappedStatement ms, MetaObject resultObject, String property, CacheKey key, Class<?> targetType);

    // 只在通过SqlSession方法获取数据库连接时才被调用
    Transaction getTransaction();

    // 只在延迟加载获取新的Executor后才会被执行
    void close(boolean forceRollback);

    // 只在延迟加载执行查询方法前被执行
    boolean isClosed();

    void setExecutorWrapper(org.apache.ibatis.executor.Executor executor);
}

interface ParameterHandler1 {

    Object getParameterObject();

    void setParameters(PreparedStatement ps) throws SQLException;

}

interface ResultSetHandler1 {
    // 会在除存储过程及返回值类型为Cursor<T>以外的查询方法中被调用
    // 由于这个接口被调用的位置在处理二级缓存之前，因此通过这种方式处理的结果可以执行二
    //级缓存。在
    <E> List<E> handleResultSets(Statement stmt) throws SQLException;

    // 3.4.0版本中新增加的，只会在返回值类型为Cursor<T>的查询方法中被调用
    <E> Cursor<E> handleCursorResultSets(Statement stmt) throws SQLException;

    // 只在使用存储过程处理出参时被调用
    void handleOutputParameters(CallableStatement cs) throws SQLException;

}

interface StatementHandler1 {
    // 会在数据库执行前被调用，优先于当前接口中的其他方法而被执行
    Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException;

    //在prepare方法之后执行，用于处理参数信息
    void parameterize(Statement statement) throws SQLException;

    // 全局设置配置defaultExecutorType="BATCH"时，执行数据操作才会调用该方法
    void batch(Statement statement) throws SQLException;

    int update(Statement statement) throws SQLException;

    // 执行SELECT方法时调用
    <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

    // 3.4.0版本中新增加的，只会在返回值类型为Cursor<T>的查询中被调用
    <E> Cursor<E> queryCursor(Statement statement) throws SQLException;

    BoundSql getBoundSql();

    ParameterHandler getParameterHandler();
}