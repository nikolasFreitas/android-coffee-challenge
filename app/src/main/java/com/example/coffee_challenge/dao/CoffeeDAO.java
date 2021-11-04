package com.example.coffee_challenge.dao;

import com.example.coffee_challenge.model.Coffee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CoffeeDAO {
    final private static Set<Coffee> coffeeList = new HashSet<>();

    public void add(Coffee coffee) {
        coffeeList.add(coffee);
    }

    public Optional<Coffee> findById(int coffeeId) {
        return coffeeList.stream()
                .filter(coffee -> coffee.id == coffeeId)
                .findFirst();
    }

    public boolean remove(Coffee coffee) {
        return coffeeList.remove(coffee);
    }

    public List<Coffee> getAll() {
        return new ArrayList<>(coffeeList);
    }

    public boolean isEmpty() {
        return coffeeList.isEmpty();
    }
}