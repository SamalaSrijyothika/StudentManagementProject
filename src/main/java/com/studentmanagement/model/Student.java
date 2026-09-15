package com.studentmanagement.model;

public class Student {

    public int id;
    public String name;
    public int age;
    public String course;
    public String email;
    public String gender;

    public Student(
            int id,
            String name,
            int age,
            String course,
            String email,
            String gender) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.email = email;
        this.gender = gender;
    }
}