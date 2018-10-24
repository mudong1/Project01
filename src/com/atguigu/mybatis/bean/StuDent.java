package com.atguigu.mybatis.bean;

import org.apache.ibatis.type.Alias;

/**
 * @author lhd
 */
@Alias("stu")
public class StuDent {
    private Integer id; //
    private String name; //
    private Integer age; //

    public StuDent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StuDent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
