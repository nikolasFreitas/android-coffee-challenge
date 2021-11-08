package com.example.coffee_challenge.dao;

import com.example.coffee_challenge.model.Employee;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    int add(T coffee);

    Optional<T> findById(int coffeeId);

    boolean remove(T coffee);

    boolean remove(int id);

    List<T> getAll();

    boolean isEmpty();

    void update(T employee);
}
