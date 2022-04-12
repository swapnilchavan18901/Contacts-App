package com.example.demo;

public class customerModel {
    private int id;
    private String name;
    private String Phone;

    public customerModel(int id, String name, String Phone) {
        this.id = id;
        this.name = name;
        this.Phone = Phone;
    }



    @Override
    public String toString() {
        return
                "Name= " + name +"   "+
                "Phone=" + Phone;


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

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
}
