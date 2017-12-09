package com.recruiting.service.employee.dto.model;

import com.recruiting.domain.IndividualTimeOff;

import java.util.List;
import java.util.Map;

public class EmployeeFullDetailsModel extends EmployeeModel {
    private List<IndividualTimeOff> overallApprovedTimeOffs;
    private Map<String, Long> overallTimeOff;
    private Map<String, Long> overallDisposedTimeOff;
}
