package com.example.coffee_challenge.model;

public class Coffee {
    public int id;
    public String name;
    public int roastingLevel;

    public Coffee() {
    }

    public Coffee(String name, int roastingLevel) {
        this.name = name;
        this.roastingLevel = roastingLevel;
    }

    @Override
    public String toString() {
        return "Coffee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roastingLevel=" + roastingLevel +
                '}';
    }
}
