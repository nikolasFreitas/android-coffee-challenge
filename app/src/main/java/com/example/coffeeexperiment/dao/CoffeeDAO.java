package com.example.coffeeexperiment.dao;

import com.example.coffeeexperiment.model.Coffee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
}
