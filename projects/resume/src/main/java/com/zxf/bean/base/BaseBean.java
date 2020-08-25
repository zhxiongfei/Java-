package com.zxf.bean.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @JsonIgnore
    public String getJSON() throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            return mapper.writeValueAsString(this).replace("\"", "'");
        }catch (Exception e){
            return null;
        }
    }
}
