package com.challenge.api.model;

import java.time.Instant;
import java.util.UUID;

// Implementation of Employee interface for mock data and data creation
public class EmployeeImpl implements Employee {

    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fullName;
    private Integer salary;
    private Integer age;
    private String jobTitle;
    private String email;
    private Instant contractHireDate;
    private Instant contractTerminationDate;

    public EmployeeImpl(String firstName, String lastName, int age, int salary, String jobTitle, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.age = age;
        this.salary = salary;
        this.jobTitle = jobTitle;
        this.email = email;
    }

    // Implement all Employee interface methods
    public UUID getUuid() { return uuid; }
    public void setUuid(UUID uuid) { this.uuid = uuid; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String name) { this.firstName = name; }
    public String getLastName() { return lastName; }
    public void setLastName(String name) { this.lastName = name; }
    public String getFullName() { return fullName; }
    public void setFullName(String name) { this.fullName = name; }
    public Integer getSalary() { return salary; }
    public void setSalary(Integer salary) { this.salary = salary; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Instant getContractHireDate() { return contractHireDate; }
    public void setContractHireDate(Instant date) { this.contractHireDate = date; }
    public Instant getContractTerminationDate() { return contractTerminationDate; }
    public void setContractTerminationDate(Instant date) { this.contractTerminationDate = date; }
}
