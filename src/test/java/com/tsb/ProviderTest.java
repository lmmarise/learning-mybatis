package com.tsb;

import com.tsb.dao.SysPrivilegeDao;
import com.tsb.entity.SysPrivilege;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @program: new-mybatis
 * @description:
 * @author: Arise Tang
 * @create: 2020-04-12 19:21
 **/
public class ProviderTest {
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
    public void testPrivilegeProvider(){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysPrivilegeDao privilegeDao = sqlSession.getMapper(SysPrivilegeDao.class);
            SysPrivilege privilege = privilegeDao.selectById(5L);
            System.out.println(privilege);
        }
    }

    @Test
    public void testSelectByUser() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysPrivilegeDao privilegeDao = sqlSession.getMapper(SysPrivilegeDao.class);
            SysPrivilege sysPrivilege = new SysPrivilege();
            sysPrivilege.setId(2L);
            List<SysPrivilege> sysPrivileges = privilegeDao.selectByUser(sysPrivilege);
            System.out.println(sysPrivileges.get(0));
        }
    }

    @Test
    public void testInsert() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysPrivilegeDao privilegeDao = sqlSession.getMapper(SysPrivilegeDao.class);
            SysPrivilege sysPrivilege = new SysPrivilege();
            sysPrivilege.setPrivilegeName("cxk");
            sysPrivilege.setPrivilegeUrl("/cxk.com");
            privilegeDao.insert(sysPrivilege);
            sqlSession.commit();
        }
    }

    @Test
    public void testUpdate() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysPrivilegeDao privilegeDao = sqlSession.getMapper(SysPrivilegeDao.class);
            SysPrivilege sysPrivilege = new SysPrivilege();
            sysPrivilege.setId(7L);
            sysPrivilege.setPrivilegeUrl("/jntm888");
            privilegeDao.update(sysPrivilege);
            sqlSession.commit();
        }
    }
}
