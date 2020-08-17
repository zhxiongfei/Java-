package com.zxf.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Skill extends BaseBean{
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

    public void setType(Integer level) {
        this.level = level;
    }

    @JsonIgnore
    public String getLevelString(){
        switch (level){
            case 0: return "了解";
            case 1: return "熟悉";
            case 2: return "掌握";
            default: return "精通";
        }
    }
}
