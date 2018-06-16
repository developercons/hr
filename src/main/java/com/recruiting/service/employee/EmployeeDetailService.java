package com.recruiting.service.employee;

import com.recruiting.domain.Employee;
import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.model.modelUtils.PageWrapper;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeFullDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @author Marta Ginosyan
 */

public interface EmployeeDetailService {

    EmployeeModel getEmployeeModel(String employeeId);

    EmployeeDetailsModel getEmployeeDetailsModel(String employeeId);

    Map<String, Long> getTimeOffSummaryForEmployee(Employee employee);

    Map<String, Long> getTimeOffSummaryForEmployee(String id);

    EmployeeFullDetailsModel getEmployeeFullDetailsModel(String employeeId);

    PageWrapper<IndividualTimeOff> getTimeOffsByEmployee(String id, Pageable pageable);


}
