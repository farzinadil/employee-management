package com.farzin.ems.repository;

import com.farzin.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository marks this as the data-access layer.
// It also enables Spring's exception translation for persistence errors.
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // JpaRepository already gives us common CRUD methods:
    // findAll()
    // findById(id)
    // save(entity)
    // deleteById(id)
    // existsById(id)

}
