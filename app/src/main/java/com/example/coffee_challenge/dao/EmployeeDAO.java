package com.example.coffee_challenge.dao;

import com.example.coffee_challenge.dao.Dao;
import com.example.coffee_challenge.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements Dao<Employee> {
    final private static List<Employee> employeeList = new ArrayList<>();
    private static int nextId = 0;

    @Override
    public int add(Employee employee) {
        employee.id = nextId;
        employeeList.add(employee);
        return nextId++;
    }

    @Override
    public Optional<Employee> findById(int employeeId) {
        return employeeList.stream()
                .filter(employee -> employee.id == employeeId)
                .findFirst();
    }

    @Override
    public boolean remove(Employee employee) {
        return employeeList.remove(employee);
    }
    @Override
    public boolean remove(int id) {
        Optional<Employee> employeeToRemoveOpt = employeeList.stream().filter(employee -> employee.id == id).findFirst();
        employeeToRemoveOpt.ifPresent(employeeList::remove);

        return employeeToRemoveOpt.isPresent();
    }

    @Override
    public List<Employee> getAll() {
        return employeeList;
    }

    @Override
    public boolean isEmpty() {
        return employeeList.isEmpty();
    }

    @Override
    public void update(Employee employee) {
        Optional<Employee> employeeToUpdateOpt = employeeList.stream().filter(savedEmployee -> savedEmployee.id == employee.id).findFirst();
        if (employeeToUpdateOpt.isPresent()) {
            Employee employeeToUpdate = employeeToUpdateOpt.get();
            employeeToUpdate.name = employee.name;
            employeeToUpdate.admissionDate = employee.admissionDate;
            employeeToUpdate.birthdate = employee.birthdate;
        }
    }
}
