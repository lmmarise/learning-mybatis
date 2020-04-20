package com;

import com.tsb.mbg.dao.SysRoleMapper;
import com.tsb.mbg.dao.SysUserMapper;
import com.tsb.mbg.entity.SysPrivilege;
import com.tsb.mbg.entity.SysRole;
import com.tsb.mbg.entity.SysUser;
import com.tsb.mbg.entity.SysUserExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description:  </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-15 15:22 </p>
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
    public void testExample() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 接口与xml映射
            SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);

            // 拼接查询条件
            SysUserExample example = new SysUserExample();
            // 排序   其中字段为数据库字段
            example.setOrderByClause("id desc, user_name desc");
            // 去重
            example.setDistinct(true);

            // 创建一个查询 每一个Criteria都是一个独立的查询条件
            SysUserExample.Criteria criteria = example.createCriteria();
            // >=1
            criteria.andIdGreaterThanOrEqualTo(1L);
            // <4
            criteria.andIdLessThan(4L);
            // like
            criteria.andUserNameLike("%n%");
            // 再创建一个查询
            // or
            SysUserExample.Criteria or = example.or();
            // =
            or.andUserNameEqualTo("蔡徐坤");

            // 执行查询
            List<SysUser> sysUserList = userMapper.selectByExample(example);
            for (SysUser sysUser : sysUserList) {
                System.out.println(sysUser);
            }
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = sysUserDao.selectUserAndRoleById(1001L);
            System.out.println(sysUser);
        }
    }

    @Test
    public void testSelectUserAndRoleById2() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUserList = sysUserDao.selectUserAndRoleById2(1001L);
            for (SysUser sysUser : sysUserList) {
                System.out.println(sysUser);
            }
        }
    }

    @Test
    public void testSelectUserAndRoleById3() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUserList = sysUserDao.selectUserAndRoleById3(1001L);
            for (SysUser sysUser : sysUserList) {
                System.out.println(sysUser);
            }
        }
    }

    @Test
    public void testSelectUserAndRoleByIdSelect() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUserList = sysUserDao.selectUserAndRoleByIdSelect(1001L);
            for (SysUser sysUser : sysUserList) {
                System.out.println(sysUser);
            }
        }
    }

    @Test
    public void testSelectAllUserAndRoles() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            List<SysUser> sysUserList = sysUserDao.selectAllUserAndRoles2();
            for (SysUser sysUser : sysUserList) {
                System.out.println("用户名:" + sysUser.getUserName());
                for (SysRole sysRole : sysUser.getRoleList()) {
                    System.out.println("    角色名:" + sysRole.getRoleName());
                    for (SysPrivilege sysPrivilege : sysRole.getSysPrivilegeList()) {
                        System.out.println("        权限名:" + sysPrivilege.getPrivilegeName());
                    }
                }
            }
        }
    }

    @Test
    public void testSelectAllRoleAndPrivilege() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            List<SysRole> sysRoles = sysUserDao.selectAllRoleAndPrivilege();
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.getRoleName());
                for (SysPrivilege sysPrivilege : sysRole.getSysPrivilegeList()) {
                    System.out.println("    " + sysPrivilege.getPrivilegeName());
                }
            }
        }
    }

    @Test
    public void testSelectAllRole() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysRoleMapper roleMapperDao = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> sysRoles = roleMapperDao.selectAllRole();
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole);
            }
        }
    }

    @Test
    public void testSelectRoleByUserId() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysRoleMapper roleMapperDao = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> sysRoles = roleMapperDao.selectRoleByUserId(1L);
            for (SysRole sysRole : sysRoles) {
                System.out.println("角色名:" + sysRole.getRoleName());
                for (SysPrivilege sysPrivilege : sysRole.getSysPrivilegeList()) {
                    System.out.println("    权限名:" + sysPrivilege.getPrivilegeName());
                }
            }
        }
    }

    @Test
    public void testSelectAllUserAndRolesSelect() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = sysUserDao.selectAllUserAndRolesSelect(1L);
            System.out.println("用户名:" + sysUser.getUserName());
            for (SysRole sysRole : sysUser.getRoleList()) {
                System.out.println("    角色名:" + sysRole.getRoleName());
                for (SysPrivilege sysPrivilege : sysRole.getSysPrivilegeList()) {
                    System.out.println("        权限名:" + sysPrivilege.getPrivilegeName());
                }
            }
        }
    }

    @Test
    public void selectRoleByUserIdChoose() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysRoleMapper sysUserDao = sqlSession.getMapper(SysRoleMapper.class);
            SysRole sysRole = sysUserDao.selectRoleByUserIdChoose(1L);
            System.out.println("角色名:" + sysRole.getRoleName());
            for (SysPrivilege sysPrivilege : sysRole.getSysPrivilegeList()) {
                System.out.println("    权限名:" + sysPrivilege.getPrivilegeName());
            }
        }
    }

    @Test
    public void selectUserById() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            SysUser sysUser = new SysUser();
            sysUser.setId(1L);
            sysUserDao.selectUserById(sysUser);
            System.out.println(sysUser.getUserName());
        }
    }

    // 第二个存储过程
    @Test
    public void selectUserPage() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            Map<String, Object> params = new HashMap<>();
            params.put("userName", "ad");
            params.put("offset", 0);
            params.put("limit", 10);
            // 执行查询
            List<SysUser> sysUserList = sysUserDao.selectUserPage(params);
            // 获取存储过程out的total
            Long total = (Long) params.get("total");
            System.out.println("总数:" + total);
            for (SysUser sysUser : sysUserList) {
                System.out.println("    用户名:" + sysUser.getUserName());
            }
        }
    }

    @Test
    public void insertUserAndRoles() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            SysUser user = new SysUser();
            user.setUserName("test!");
            user.setUserPassword("123456");
            user.setUserEmail("test@mybatis.tk");
            user.setUserInfo("test Info");
            user.setHeadImg(new byte[]{1, 2, 3});
            // 插入用户信息和角色关联信息
            sysUserDao.insertUserAndRoles(user, "1,2");
            sqlSession.commit();
        }
    }

    @Test
    public void deleteUserById() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysUserMapper sysUserDao = sqlSession.getMapper(SysUserMapper.class);
            sysUserDao.deleteUserById(1003L);
            sqlSession.commit();
        }
    }

    @Test
    public void selectById() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            SysRoleMapper sysUserDao = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> sysRoles = sysUserDao.selectRoleByUserId(1L);
            for (SysRole sysRole : sysRoles) {
                System.out.println(sysRole.getRoleName());
                System.out.println(sysRole.getEnabled().getValue());
            }
        }
    }

    /*-----------------------------------日期转换-----------------------------------------*/
    public static long differentDays(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new RuntimeException("日期不能为空");
        }
        LocalDate localDate1 = date2LocalDate(date1);
        LocalDate localDate2 = date2LocalDate(date2);
        return localDate1.until(localDate2, ChronoUnit.DAYS);
    }

    public static LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return localDate;
    }

    public static void main(String[] args) {
        System.out.println(differentDays(new Date(), new Date()));
    }

    /**
     * date转日期字符串
     */
    public static String getStringDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
}
