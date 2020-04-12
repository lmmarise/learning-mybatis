package com.tsb.dao;

import com.tsb.entity.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色表(SysRole)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-12 18:30:29
 */
public interface SysRoleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Select({"select id, role_name roleName, enabled, create_by createBy, create_time createTime",
            "form sys_role",
            "where id = #{id}"})    // 也可以写在同一行
    @Results(id = "roleResultMap", value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "roleName", column = "role_name"),
            @Result(property = "enabled", column = "enabled"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "createTime", column = "create_time")
    })
    SysRole selectById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<SysRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRole 实例对象
     * @return 对象列表
     */
    List<SysRole> queryAll(SysRole sysRole);

    @ResultMap("roleResultMap")
    @Select({"select * from sys_role"})
        // mapUnderscoreToCamelCase配置, 驼峰自动转换
    List<SysRole> selectAll();

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    @Insert({
            "insert into sys_role(id, role_name, enabled, create_by, create_time)",
            "values(#{id}, #{roleName}, #{enabled}, #{createBy}, #{createTime, jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    // 添加额外设置
    int insert(SysRole sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int update(SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    @Delete({"delete from sys_role where id = #{id}"})
    int deleteById(Long id);

    @Update({
            "update sys_role",
            "set role_name = #{roleName}, ",
            "enabled = #{enabled}, ",
            "create_by = #{createBy}, ",
            "create_time = #{createTime, jdbcType=TIMESTAMP}",
            "where id = #{id}"
    })
    int updateById(Long id);


}