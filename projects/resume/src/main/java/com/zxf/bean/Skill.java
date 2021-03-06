package com.zxf.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zxf.bean.base.BaseBean;

public class Skill extends BaseBean {
    private String name;
    private Integer level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @JsonIgnore
    public String getLevelString(){
        switch (level){
            case 1: return "熟悉";
            case 2: return "掌握";
            case 3: return "精通";
            default: return "了解";
        }
    }
}
