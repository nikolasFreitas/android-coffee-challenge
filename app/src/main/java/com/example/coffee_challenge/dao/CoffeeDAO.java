package com.example.coffee_challenge.dao;

import com.example.coffee_challenge.model.Coffee;
import com.example.coffee_challenge.model.Employee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CoffeeDAO implements Dao<Coffee> {
    final private static List<Coffee> coffeeList = new ArrayList<>();
    private static int nextId = 0;

    public int add(Coffee coffee) {
        coffee.id = nextId;
        coffeeList.add(coffee);
        return nextId++;
    }

    public Optional<Coffee> findById(int coffeeId) {
        return coffeeList.stream()
                .filter(coffee -> coffee.id == coffeeId)
                .findFirst();
    }

    public boolean remove(Coffee coffee) {
        return coffeeList.remove(coffee);
    }
    public boolean remove(int id) {
        Optional<Coffee> coffeeToRemoveOpt = coffeeList.stream().filter(coffee -> coffee.id == id).findFirst();
        coffeeToRemoveOpt.ifPresent(coffeeList::remove);

        return coffeeToRemoveOpt.isPresent();
    }

    public List<Coffee> getAll() {
        return coffeeList;
    }

    public boolean isEmpty() {
        return coffeeList.isEmpty();
    }

    public void update(Coffee coffee) {
        Optional<Coffee> coffeeToUpdateOpt = coffeeList.stream().filter(savedCoffee -> savedCoffee.id == coffee.id).findFirst();
        if (coffeeToUpdateOpt.isPresent()) {
            Coffee coffeeToUpdate = coffeeToUpdateOpt.get();
            coffeeToUpdate.name = coffee.name;
            coffeeToUpdate.roastingLevel = coffee.roastingLevel;
        }
    }
}
