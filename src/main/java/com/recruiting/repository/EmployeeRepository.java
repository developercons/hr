package com.recruiting.repository;

import com.recruiting.domain.Employee;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

public interface EmployeeRepository extends BaseRepository<Employee> {

    List<Employee> findAllByDtypeAndDtypeNotNull(String dtype);
    Employee findByUsername(String username);
}
