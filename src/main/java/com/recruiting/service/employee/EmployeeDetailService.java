package com.recruiting.service.employee;

import com.recruiting.domain.Employee;
import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.service.employee.dto.model.EmployeeModel;

import java.util.Map;

public interface EmployeeDetailService {

    EmployeeModel getEmployeeModel(Long employeeId);
    void saveIndividualTimeOff(IndividualTimeOff individualTimeOffDTO);
    Map<String, Long> getTimeOffSummaryForEmployee(Employee employee);


}
