package com.farzin.ems.mapper;

import com.farzin.ems.dto.EmployeeDto;
import com.farzin.ems.entity.Employee;

// Mapper class converts between Entity and DTO.
// Entity is used for database persistence.
// DTO is used for API request/response payloads.
public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
