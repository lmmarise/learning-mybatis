package com.tsb.mbg.type;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: sys_role表中存在一个宇段enabled, 这个字段只有两个可选值, 为禁用，1为启用。 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-18 10:11 </p>
 **/
public enum Enabled {
    disabled(0),   // 禁用
    enabled(1);    // 启用

    private final int value;

    Enabled(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
