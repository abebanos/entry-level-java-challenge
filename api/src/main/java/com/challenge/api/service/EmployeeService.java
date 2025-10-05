package com.challenge.api.service;

import com.challenge.api.model.Employee;
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
        employee.setContractHireDate(Instant.now());
        employees.put(employee.getUuid(), employee);
        return employee;
    }
}
