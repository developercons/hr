package com.recruiting.repository;

import com.recruiting.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

public interface EmployeeRepository extends BaseRepository<Employee> {

    List<Employee> findAllByDtypeAndDtypeNotNullAndLeavedFalse(String dtype);

    Employee findByUsername(String username);

    Page<Employee> findAllByDtypeAndDtypeNotNull(String dtype, Pageable pageable);

    Page<Employee> findAllBy(Pageable pageable);
}
