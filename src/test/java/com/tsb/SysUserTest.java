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
import java.util.List;

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
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            SysUserDao sysUserDao = sqlSession.getMapper(SysUserDao.class);
            SysUser user = new SysUser();
            user.setId(4L);
            List<SysUser> roleList = sysUserDao.selectByIdOrUserName(user);
            for (SysUser role : roleList) {
                System.out.println(role);
            }
        }
    }
}
