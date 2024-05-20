package com.example.form;

import jakarta.validation.constraints.NotBlank;

public class UserForm {
    @NotBlank(message = "入力必須です。")
    private String name;

    @NotBlank(message = "入力必須です。")
    private String age;

    @NotBlank(message = "入力必須です。")
    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", comment='" + comment + '\'' +
                '}';
    }
}
