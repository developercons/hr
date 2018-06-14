package com.recruiting.service.employee.dto.model;

import com.recruiting.entity.IndividualTimeOff;

import java.util.List;
import java.util.Map;

/**
 * @author Marta Ginosyan
 */

public class EmployeeFullDetailsModel extends EmployeeDetailsModel {
    private List<IndividualTimeOff> overallApprovedTimeOffs;
    private Map<String, Long> overallTimeOff;
    private Map<String, Long> overallDisposedTimeOff;

    public List<IndividualTimeOff> getOverallApprovedTimeOffs() {
        return overallApprovedTimeOffs;
    }

    public void setOverallApprovedTimeOffs(List<IndividualTimeOff> overallApprovedTimeOffs) {
        this.overallApprovedTimeOffs = overallApprovedTimeOffs;
    }

    public Map<String, Long> getOverallTimeOff() {
        return overallTimeOff;
    }

    public void setOverallTimeOff(Map<String, Long> overallTimeOff) {
        this.overallTimeOff = overallTimeOff;
    }

    public Map<String, Long> getOverallDisposedTimeOff() {
        return overallDisposedTimeOff;
    }

    public void setOverallDisposedTimeOff(Map<String, Long> overallDisposedTimeOff) {
        this.overallDisposedTimeOff = overallDisposedTimeOff;
    }
}
