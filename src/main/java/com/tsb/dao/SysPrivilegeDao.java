package com.tsb.dao;

import com.tsb.entity.SysPrivilege;
import com.tsb.provider.PrivilegeProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 权限表(SysPrivilege)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-12 19:11:07
 */
public interface SysPrivilegeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @SelectProvider(type = PrivilegeProvider.class, method = "selectById")
    SysPrivilege selectById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysPrivilege> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysPrivilege 实例对象
     * @return 对象列表
     */
    List<SysPrivilege> queryAll(SysPrivilege sysPrivilege);


    List<SysPrivilege> selectByUser(SysPrivilege sysPrivilege);

    /**
     * 新增数据
     *
     * @param sysPrivilege 实例对象
     * @return 影响行数
     */
    int insert(SysPrivilege sysPrivilege);

    /**
     * 修改数据
     *
     * @param sysPrivilege 实例对象
     * @return 影响行数
     */
    int update(SysPrivilege sysPrivilege);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}