package com.tsb.mbg.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: 创建信息 <br>
 * selectAllRoleAndPrivileges方法的create_by和create_time两个字字段 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-17 13:44 </p>
 **/
public class CreateInfo implements Serializable {
    // 创建人
    private String createBy;
    // 创建时间
    private Date createTime;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CreateInfo{" +
                "createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
