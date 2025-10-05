package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class EmployeeService {

    private final Map<UUID, Employee> employees = new HashMap<>();

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(UUID uuid) {
        return employees.get(uuid);
    }

    public Employee createEmployee(Employee employee) {
        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }
        // Assuming employee is officially on their hired status when this webhook is fired and they are in DB
        employee.setContractHireDate(Instant.now());
        employees.put(employee.getUuid(), employee);
        return employee;
    }

    // Mock data generation for testing and to simulate current employee management data that is to be exposed
    @PostConstruct
    private void initMockData() {
        createEmployee(new EmployeeImpl("Alice", "Smith", 30, 70000, "Engineer", "alice@example.com"));
        createEmployee(new EmployeeImpl("Bob", "Johnson", 45, 90000, "Manager", "bob@example.com"));
        createEmployee(new EmployeeImpl("Carol", "Williams", 28, 60000, "Analyst", "carol@example.com"));
    }
}
