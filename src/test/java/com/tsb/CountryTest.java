package com.tsb;

import com.tsb.dao.CountryDao;
import com.tsb.dao.SysUserDao;
import com.tsb.entity.Country;
import com.tsb.entity.SysRole;
import com.tsb.entity.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Date;
import java.util.List;

/**
 * @program: new-mybatis
 * @description:
 * @author: Arise Tang
 * @create: 2020-04-11 21:16
 **/
public class CountryTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    @Test
    public void testSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        CountryDao countryDao = sqlSession.getMapper(CountryDao.class);
        Country country = countryDao.queryById(6);
        System.out.println(country);
    }

    @Test
    public void testSelectRolesByUserId() {
        try (SqlSession sqlSession = getSqlSession()) {
            SysUserDao userDao = sqlSession.getMapper(SysUserDao.class);
            List<SysRole> roleList = userDao.selectRolesByUserId(1L);
            for (SysRole role : roleList) {
                System.out.println(role);
            }
        }
    }

    @Test
    public void testInsert(){
        try (SqlSession sqlSession = getSqlSession()) {
            SysUserDao userDao = sqlSession.getMapper(SysUserDao.class);
            SysUser user = new SysUser();
            user.setUserName("cxk");
            user.setUserPassword("jntm");
            user.setUserEmail("cxk.@qq.com");
            user.setUserInfo("cxk test");
            user.setHeadImg(new byte[]{1, 2, 3});
            user.setCreateTime(new Date());
            int result = userDao.insert(user);
            sqlSession.commit();
            System.out.println("result=" + result);
        }
    }

    @Test
    public void testUpdate(){
        try (SqlSession sqlSession = getSqlSession()) {
            SysUserDao userDao = sqlSession.getMapper(SysUserDao.class);
            SysUser user = new SysUser();
            user.setId(4L);
            user.setUserEmail("cxk@qq.com");
            int result = userDao.update(user);
            sqlSession.commit();
            System.out.println("result=" + result);
        }
    }

    @Test
    public void testSelectRolesByUserIdAndRoleEnabled(){
        try (SqlSession sqlSession = getSqlSession()) {
            SysUserDao userDao = sqlSession.getMapper(SysUserDao.class);
            List<SysRole> userList = userDao.selectRolesByUserIdAndRoleEnabled(1L, 1);
            System.out.println(userList.get(0));
        }
    }
}
