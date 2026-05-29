package com.farzin.ems.controller;

import com.farzin.ems.dto.EmployeeDto;
import com.farzin.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController = @Controller + @ResponseBody.
// It tells Spring this class exposes REST APIs and returns JSON responses.
@RestController

// Base URL for all endpoints in this controller.
@RequestMapping("/api/v1/employees")

// Lombok creates constructor for dependency injection.
@AllArgsConstructor
public class EmployeeController {

    // Controller depends on service layer, not repository directly.
    private EmployeeService employeeService;

    // POST /api/v1/employees
    // Creates a new employee.
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        // @RequestBody converts JSON request body into EmployeeDto.
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        // Return HTTP 201 Created.
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // GET /api/v1/employees/{id}
    // Gets employee by id.
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        // @PathVariable reads the id from the URL.
        EmployeeDto employeeDto = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employeeDto);
    }

    // GET /api/v1/employees
    // Gets all employees.
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    // PUT /api/v1/employees/{id}
    // Updates an existing employee.
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDto employeeDto
    ) {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);

        return ResponseEntity.ok(updatedEmployee);
    }

    // DELETE /api/v1/employees/{id}
    // Deletes an employee.
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);

        return ResponseEntity.ok("Employee deleted successfully.");
    }
}
