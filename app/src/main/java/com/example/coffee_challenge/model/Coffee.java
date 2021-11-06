package com.example.coffee_challenge.model;

import java.io.Serializable;

public class Coffee implements Serializable {

    public int id;
    public String name;
    public int roastingLevel;

    public Coffee() {
    }

    public Coffee(String name, int roastingLevel, int id) {
        this.name = name;
        this.roastingLevel = roastingLevel;
        this.id = id;
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
