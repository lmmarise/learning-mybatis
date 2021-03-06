package com.tsb.mbg.entity;

import com.tsb.mbg.type.Enabled;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Database Table Remarks:
 *   角色表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table sys_role
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class SysRole implements Serializable {
    /**
     * <p>角色ID </p>
     * <p>id </p>
     */
    private Long id;

    /**
     * <p>角色名 </p>
     * <p>role_name </p>
     */
    private String roleName;

    /**
     * <p>有效标志 </p>
     * <p>enabled </p>
     */
    private Enabled enabled;

    /**
     * <p>创建人 </p>
     * <p>create_by </p>
     */
    private Long createBy;

    /**
     * <p>创建时间 </p>
     * <p>create_time </p>
     */
    private Date createTime;

    /**
     * 角色权限列表
     */
    private List<SysPrivilege> sysPrivilegeList;

    /**
     * 创建人信息
     */
    private CreateInfo createInfo;

    public Enabled getEnabled() {
        return enabled;
    }

    public void setEnabled(Enabled enabled) {
        this.enabled = enabled;
    }

    public CreateInfo getCreateInfo() {
        return createInfo;
    }

    public void setCreateInfo(CreateInfo createInfo) {
        this.createInfo = createInfo;
    }

    public List<SysPrivilege> getSysPrivilegeList() {
        return sysPrivilegeList;
    }

    public void setSysPrivilegeList(List<SysPrivilege> sysPrivilegeList) {
        this.sysPrivilegeList = sysPrivilegeList;
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.id
     *
     * @return the value of sys_role.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.id
     *
     * @param id the value for sys_role.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.role_name
     *
     * @return the value of sys_role.role_name
     *
     * @mbg.generated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.role_name
     *
     * @param roleName the value for sys_role.role_name
     *
     * @mbg.generated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.create_by
     *
     * @return the value of sys_role.create_by
     *
     * @mbg.generated
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.create_by
     *
     * @param createBy the value for sys_role.create_by
     *
     * @mbg.generated
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.create_time
     *
     * @return the value of sys_role.create_time
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.create_time
     *
     * @param createTime the value for sys_role.create_time
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_role
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "SysRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", enabled=" + enabled +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", sysPrivilegeList=" + sysPrivilegeList +
                ", createInfo=" + createInfo +
                '}';
    }
}