package com.tsb;

import com.tsb.dao.SysRoleDao;
import com.tsb.dao.SysUserDao;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: new-mybatis
 * @description:
 * @author: Arise Tang
 * @create: 2020-04-12 21:56
 **/
public class SysUserTest {
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

    @Test
    public void testSelectByIdOrUserName() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserDao sysUserDao = sqlSession.getMapper(SysUserDao.class);
            SysUser user = new SysUser();
            user.setId(4L);
            List<SysUser> roleList = sysUserDao.selectByIdOrUserName(user);
            for (SysUser role : roleList) {
                System.out.println(role);
            }
        }
    }

    @Test
    public void testSelectByIdList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserDao sysUserDao = sqlSession.getMapper(SysUserDao.class);
            Long[] ids = new Long[]{1L, 2L};
            List<SysUser> userList = sysUserDao.selectByIdList(ids);
            for (SysUser user : userList) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void testInsertList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserDao sysUserDao = sqlSession.getMapper(SysUserDao.class);
            List<SysUser> list = new ArrayList<>();
            list.add(new SysUser("cxk", "123456"));
            list.add(new SysUser("ji", "ntm"));
            list.add(new SysUser("lanQiu", "cxk"));
            System.out.println("插入了" + sysUserDao.insertList(list) + "行");
            sqlSession.commit();
        }
    }

    @Test
    public void testUpdateByMap() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserDao sysUserDao = sqlSession.getMapper(SysUserDao.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 4L);
            map.put("user_name", "蔡徐坤");
            map.put("user_password", "基尼实在是太美");
            sysUserDao.updateByMap(map);
            sqlSession.commit();
        }
    }

    @Test
    public void testSelectByUserName() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserDao sysUserDao = sqlSession.getMapper(SysUserDao.class);
            SysUser user = new SysUser();
            user.setUserName("坤");
            List<SysUser> userList = sysUserDao.selectByUserName(user);
            for (SysUser u : userList) {
                System.out.println(u);
            }
        }
    }
}
