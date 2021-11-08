package com.example.coffee_challenge.model;

import androidx.annotation.NonNull;

import java.util.Date;

public class Employee {
    public int id;
    public String name;
    public Date birthdate;
    public Date admissionDate;

    public Employee() {
    }

    public Employee(int id, String name, Date birthdate, Date admissionDate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
    }

    public Employee(String name, Date birthdate, Date admissionDate) {
        this.name = name;
        this.birthdate = birthdate;
        this.admissionDate = admissionDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", admissionDate=" + admissionDate +
                '}';
    }
}
