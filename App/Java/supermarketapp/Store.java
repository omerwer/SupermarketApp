package com.example.supermarketapp;
//NOT NEEDED TODO
public class Store {
    private String name;
    //location etc?

    public Store(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
