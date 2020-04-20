package com.tsb.mbg.plugins.pagehelper;

import org.apache.ibatis.session.RowBounds;

/**
 * <p>@program: new-mybatis </p>
 * <p>@description: 可以记录total的分页参数 </p>
 * <p>@author: Arise Tang </p>
 * <p>@create: 2020-04-19 17:45 </p>
 **/
public class PageRowBounds extends RowBounds {
    private long total;

    public PageRowBounds() {
        super();
    }

    public PageRowBounds(int offset, int limit) {
        super(offset, limit);
    }

    public long getTotal(){
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
