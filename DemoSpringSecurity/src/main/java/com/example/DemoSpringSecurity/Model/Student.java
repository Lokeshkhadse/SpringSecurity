package com.example.DemoSpringSecurity.Model;

public class Student {
    private int id;
    private String name;
    private String marks;

    public Student(int id, String name, String marks) {
        super();
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public Student() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks='" + marks + '\'' +
                '}';
    }
}
