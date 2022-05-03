package com.example.demo;

public class customerModel {
    public String name;
    public static int id;
    public String Phone;

    public customerModel(int id, String name, String Phone) {
        this.id = id;
        this.name = name;
        this.Phone = Phone;

    }

    public static int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPhone() {
        return Phone;
    }


}
