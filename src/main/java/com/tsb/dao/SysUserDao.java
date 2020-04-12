package com.tsb.dao;

import com.tsb.entity.SysRole;
import com.tsb.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户表(SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-11 21:53:44
 */
public interface SysUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    List<SysUser> queryAll(SysUser sysUser);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 通过用户id查用户权限
     *
     * @param userId 用户id
     * @return 用户权限信息实例
     */
    List<SysRole> selectRolesByUserId(Long userId);

    /**
     * 根据用户id和角色enable状态获取用户的角色
     *
     * @param userId  用户id
     * @param enabled 状态
     * @return 用户角色
     */
    List<SysRole> selectRolesByUserIdAndRoleEnabled(@Param("userId") Long userId,
                                                    @Param("enabled") Integer enabled);


    /*List<SysRole> selectRolesByUserIdAndRole(
            @Param("user") SysUser user,
            @Param("role") SysRole role);
*/


    List<SysUser> selectByIdOrUserName(SysUser sysUser);
}