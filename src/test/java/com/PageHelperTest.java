package com;

import com.tsb.mbg.dao.SysRoleMapper;
import com.tsb.mbg.entity.SysRole;
import com.tsb.mbg.plugins.pagehelper.PageRowBounds;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: 自定义的pageHelper的测试 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-19 18:23 </p>
 **/
public class PageHelperTest extends BaseMapperTest{
    @Test
    public void selectAllByRowBounds(){
        try(SqlSession sqlSession = getSqlSessionFactory().openSession()){
            SysRoleMapper roleMapper = sqlSession.getMapper(SysRoleMapper.class);
            // 使用RowBounds查询
            RowBounds rowBounds = new RowBounds(0, 1);
            List<SysRole> roleList = roleMapper.selectAll(rowBounds);
            for (SysRole sysRole : roleList) {
                System.out.println("角色名: " + sysRole.getRoleName());
            }
            // 使用PageRowBounds查询  会额外查询总数
            PageRowBounds pageRowBounds = new PageRowBounds(0, 1);
            roleList = roleMapper.selectAll(pageRowBounds);
            System.out.println("查询总数: " + pageRowBounds.getTotal());
            for (SysRole sysRole : roleList) {
                System.out.println("角色名: " + sysRole.getRoleName());
            }
            // 再次查询获取第二个角色
            pageRowBounds = new PageRowBounds(1, 1);
            roleList = roleMapper.selectAll(pageRowBounds);
            // 获取总数
            System.out.println("查询总数: " + pageRowBounds.getTotal());
            for (SysRole sysRole : roleList) {
                System.out.println("角色名: " + sysRole.getRoleName());
            }
        }
    }
}
