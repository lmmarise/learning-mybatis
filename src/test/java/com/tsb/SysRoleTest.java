package com.tsb;

import com.tsb.dao.SysRoleDao;
import com.tsb.entity.SysRole;
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
 * @create: 2020-04-12 18:49
 **/
public class SysRoleTest {
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
    public void testSelectAll() {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            SysRoleDao sysRoleDao = sqlSession.getMapper(SysRoleDao.class);
            List<SysRole> roleList = sysRoleDao.selectAll();
            for (SysRole role : roleList) {
                System.out.println(role);
            }
        }
    }
}
