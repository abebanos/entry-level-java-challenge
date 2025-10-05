package com.challenge.api;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.challenge.api.controller.EmployeeController;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private EmployeeImpl testEmployee;

    @BeforeEach
    void setUp() {
        testEmployee = new EmployeeImpl("Alice", "Smith", 30, 70000, "Engineer", "alice@example.com");
        testEmployee.setUuid(UUID.randomUUID());
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeService.getAllEmployees()).thenReturn(List.of(testEmployee));

        mockMvc.perform(get("/api/v1/employee"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Alice"));
    }

    @Test
    void testGetEmployeeByUuid() throws Exception {
        UUID uuid = testEmployee.getUuid();
        when(employeeService.getEmployee(uuid)).thenReturn(testEmployee);

        mockMvc.perform(get("/api/v1/employee/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        when(employeeService.createEmployee(any(EmployeeImpl.class))).thenReturn(testEmployee);

        String json = objectMapper.writeValueAsString(testEmployee);

        mockMvc.perform(post("/api/v1/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.jobTitle").value("Engineer"));
    }
}
