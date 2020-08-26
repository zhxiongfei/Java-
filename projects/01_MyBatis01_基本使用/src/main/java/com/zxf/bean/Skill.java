package com.zxf.bean;

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

    @Override
    public String toString() {
        return "Skill{" +
                "createdTime" + getCreatedTime() + '\'' +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
