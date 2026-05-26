package com.farzin.ems.service;

import com.farzin.ems.dto.EmployeeDto;

import java.util.List;

// Service interface defines the business operations.
// The controller depends on this abstraction instead of directly using the repository.
public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

    void deleteEmployee(Long employeeId);
}
