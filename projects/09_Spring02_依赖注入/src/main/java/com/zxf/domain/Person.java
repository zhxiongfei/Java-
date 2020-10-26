package com.zxf.domain;

import java.math.BigDecimal;
import java.util.Arrays;

public class Person {
    private int age;
    private String name;
    private BigDecimal money;
    private Dog dog;
    private String[] nickNames;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public void setNickNames(String[] nickNames) {
        this.nickNames = nickNames;
    }

    public String[] getNickNames() {
        return nickNames;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", dog=" + dog +
                '}';
    }
}
