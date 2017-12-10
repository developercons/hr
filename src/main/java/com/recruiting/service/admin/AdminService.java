package com.recruiting.service.admin;

import com.recruiting.model.modelUtils.PageWrapper;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

public interface AdminService {

    List<EmployeeModel> getEmployees();
    PageWrapper<EmployeeModel> getEmployee(Pageable pageable);
    PageWrapper<EmployeeDetailsModel> getEmployeeDetails(Pageable pageable);
}
