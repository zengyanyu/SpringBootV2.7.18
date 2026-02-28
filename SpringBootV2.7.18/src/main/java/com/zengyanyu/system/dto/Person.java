package com.zengyanyu.system.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Person {

    private String name;

    private Integer age;

    private String grande;

    public Person(String name, Integer age, String grande) {
        this.name = name;
        this.age = age;
        this.grande = grande;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", grande='" + grande + '\'' +
                '}';
    }
}
