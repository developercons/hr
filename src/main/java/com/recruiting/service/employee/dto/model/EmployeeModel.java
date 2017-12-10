package com.recruiting.service.employee.dto.model;

import com.recruiting.utils.ValidationPatternUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Marta Ginosyan
 */

public class EmployeeModel implements Serializable {

    private Long employeeId;
    private String employeeName;
    private LocalDateTime joinDate;
    private LocalDateTime leaveDate;
    private Double overallVacationLeft;
    private Double overallVacationInAdvance;
    private Double overallVacationGranted;
    private Double overallDisposableVacationTaken;
    private Double overALLDisposedVacationTaken;


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDateTime getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDateTime leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Double getOverallVacationLeft() {
        return overallVacationLeft;
    }

    public void setOverallVacationLeft(Double overallVacationLeft) {
        this.overallVacationLeft = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(overallVacationLeft));
    }

    public Double getOverallVacationInAdvance() {
        return overallVacationInAdvance;
    }

    public void setOverallVacationInAdvance(Double overallVacationInAdvance) {
        this.overallVacationInAdvance = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(overallVacationInAdvance));
    }

    public Double getOverallVacationGranted() {
        return overallVacationGranted;
    }

    public void setOverallVacationGranted(Double overallVacationGranted) {
        this.overallVacationGranted = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(overallVacationGranted));
    }

    public Double getOverallDisposableVacationTaken() {
        return overallDisposableVacationTaken;
    }

    public void setOverallDisposableVacationTaken(Double overallDisposableVacationTaken) {
        this.overallDisposableVacationTaken = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(overallDisposableVacationTaken));
    }

    public Double getOverALLDisposedVacationTaken() {
        return overALLDisposedVacationTaken;
    }

    public void setOverALLDisposedVacationTaken(Double overALLDisposedVacationTaken) {
        this.overALLDisposedVacationTaken = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(overALLDisposedVacationTaken));
    }
}
