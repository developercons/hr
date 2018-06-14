package com.recruiting.service.admin;

import com.recruiting.entity.Employee;
import com.recruiting.model.modelUtils.PageWrapper;
import com.recruiting.repository.EmployeeRepository;
import com.recruiting.service.employee.EmployeeDetailService;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marta Ginosyan
 */

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private EmployeeDetailService employeeDetailService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeModel> getEmployees() {
        List<Employee> employees = employeeRepository.findAllByDtypeAndDtypeNotNullAndLeavedFalse("Employee");
        List<EmployeeModel> employeesModel = new ArrayList<>();
        if(employees.isEmpty()) {
            return employeesModel;
        }
        employees.forEach(employee -> employeesModel.add(employeeDetailService.getEmployeeModel(employee.getId())));
        return employeesModel;
    }

    @Override
    public PageWrapper<EmployeeModel> getEmployee(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllBy(pageable);
        Page<EmployeeModel> employeePage = employees.map(source -> employeeDetailService.getEmployeeModel(source.getId()));
        if(!employeePage.hasContent()){
            return new PageWrapper<>();
        }
        PageWrapper<EmployeeModel> pageWrapper = new PageWrapper<>(employeePage, "");
        return pageWrapper;
    }

    @Override
    public PageWrapper<EmployeeDetailsModel> getEmployeeDetails(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllBy(pageable);
        Page<EmployeeDetailsModel> employeePage = employees.map(source -> employeeDetailService.getEmployeeDetailsModel(source.getId()));
        if(!employeePage.hasContent()){
            return new PageWrapper<>();
        }
        PageWrapper<EmployeeDetailsModel> pageWrapper = new PageWrapper<>(employeePage, "");
        return pageWrapper;
    }
}
