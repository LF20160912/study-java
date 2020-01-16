package com.mike.study;

public class Student {
    private Integer age;
    private String name;
    private Integer id;

    public Integer getAge() {
        System.out.println("获取age:" + age);
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        System.out.println("获取name:" + name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
