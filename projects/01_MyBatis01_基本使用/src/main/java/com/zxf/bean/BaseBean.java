package com.zxf.bean;

import java.text.SimpleDateFormat;

import java.util.Date;

public abstract class BaseBean {

    private Integer id;
    private Date createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
