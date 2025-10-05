package com.challenge.api.service;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import jakarta.annotation.PostConstruct;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final Map<UUID, Employee> employees = new ConcurrentHashMap<UUID, Employee>() {};

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public List<Employee> getAllEmployees() {
        logger.info("Fetching all employees (count={})", employees.size());
        return new ArrayList<>(employees.values());
    }

    public Employee getEmployee(UUID uuid) {
        Employee employee = employees.get(uuid);
        if (employee != null) {
            logger.info("Fetched employee: {} ({})", employee.getFullName(), uuid);
        } else {
            logger.warn("Employee not found: {}", uuid);
        }
        return employee;
    }

    public Employee createEmployee(Employee employee) {

        if (employee.getUuid() == null) {
            employee.setUuid(UUID.randomUUID());
        }
        // Assuming employee is officially on their hired status when this webhook is fired and they are in DB
        employee.setContractHireDate(Instant.now());
        employees.put(employee.getUuid(), employee);
        logger.info("Created employee: {} ({})", employee.getFullName(), employee.getUuid());
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
