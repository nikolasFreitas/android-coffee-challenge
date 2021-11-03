package com.example.coffeeexperiment.model;

public class Coffee {
    public int id;
    public String name;
    public int roastingLevel;

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roastingLevel=" + roastingLevel +
                '}';
    }
}
