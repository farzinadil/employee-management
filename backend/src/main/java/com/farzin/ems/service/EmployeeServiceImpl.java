package com.farzin.ems.service;

import com.farzin.ems.dto.EmployeeDto;
import com.farzin.ems.entity.Employee;
import com.farzin.ems.exception.ResourceNotFoundException;
import com.farzin.ems.mapper.EmployeeMapper;
import com.farzin.ems.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// @Service marks this class as a Spring-managed service bean.
// This is where business logic belongs.
@Service

// Lombok generates a constructor for final fields.
// Spring uses that constructor for dependency injection.
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    // Constructor injection is preferred because dependencies are required and easier to test.
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        // Convert DTO from API layer into JPA entity for persistence.
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        // save() performs insert or update depending on entity state.
        Employee savedEmployee = employeeRepository.save(employee);

        // Convert saved entity back to DTO for API response.
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        // findById returns Optional because the record may not exist.
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with id: " + employeeId)
                );

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        // Convert list of entities to list of DTOs.
        return employees.stream()
                .map(EmployeeMapper::mapToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        // First confirm the employee exists.
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with id: " + employeeId)
                );

        // Update fields on the managed entity.
        existingEmployee.setFirstName(employeeDto.getFirstName());
        existingEmployee.setLastName(employeeDto.getLastName());
        existingEmployee.setEmail(employeeDto.getEmail());

        // Save updated entity.
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee does not exist with id: " + employeeId)
                );

        employeeRepository.delete(existingEmployee);
    }
}
