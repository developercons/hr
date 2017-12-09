package com.recruiting.service.employee.dto.model;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.utils.ValidationPatternUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class EmployeeModel implements Serializable{

    private Long employeeId;
    private String employeeName;
    private LocalDateTime joinDate;
    private LocalDateTime leaveDate;
    private Map<String, Long> timeOffSummary;
    private IndividualTimeOff newIndividualTimeOff;
    private List<IndividualTimeOff> acceptedIndividualTimeOffs;
    private List<IndividualTimeOff> rejectedIndividualTimeOffs;
    private List<IndividualTimeOff> pendingTimeOffs;

    private Double vacationEarnedFromIndate;
    private Double indateDuration;
    private Double vacationEarnedFromOutdated;
    private Double outdateDuration;
    private Double overallVacationGranted;
    private Double overallDisposableVacationTaken;
    private Double overALLDisposedVacationTaken;
    private Double overallTimeOffTaken;

    private VacationConsumptionModel balancedVacationConsumption;
    private VacationConsumptionModel disposedFromOutdatedVacationConsumption;
    private VacationConsumptionModel disposedFromIndateVacationConsuption;


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

    public Map<String, Long> getTimeOffSummary() {
        return timeOffSummary;
    }

    public void setTimeOffSummary(Map<String, Long> timeOffSummary) {
        this.timeOffSummary = timeOffSummary;
    }

    public IndividualTimeOff getNewIndividualTimeOff() {
        return newIndividualTimeOff;
    }

    public void setNewIndividualTimeOff(IndividualTimeOff newIndividualTimeOff) {
        this.newIndividualTimeOff = newIndividualTimeOff;
    }

    public List<IndividualTimeOff> getAcceptedIndividualTimeOffs() {
        return acceptedIndividualTimeOffs;
    }

    public void setAcceptedIndividualTimeOffs(List<IndividualTimeOff> acceptedIndividualTimeOffs) {
        this.acceptedIndividualTimeOffs = acceptedIndividualTimeOffs;
    }

    public List<IndividualTimeOff> getRejectedIndividualTimeOffs() {
        return rejectedIndividualTimeOffs;
    }

    public void setRejectedIndividualTimeOffs(List<IndividualTimeOff> rejectedIndividualTimeOffs) {
        this.rejectedIndividualTimeOffs = rejectedIndividualTimeOffs;
    }

    public List<IndividualTimeOff> getPendingTimeOffs() {
        return pendingTimeOffs;
    }

    public void setPendingTimeOffs(List<IndividualTimeOff> pendingTimeOffs) {
        this.pendingTimeOffs = pendingTimeOffs;
    }

    public Double getVacationEarnedFromIndate() {
        return vacationEarnedFromIndate;
    }

    public void setVacationEarnedFromIndate(Double vacationEarnedFromIndate) {
        this.vacationEarnedFromIndate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationEarnedFromIndate));
    }

    public Double getIndateDuration() {
        return indateDuration;
    }

    public void setIndateDuration(Double indateDuration) {
        this.indateDuration = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(indateDuration));
    }

    public Double getVacationEarnedFromOutdate() {
        return vacationEarnedFromOutdated;
    }

    public void setVacationEarnedFromOutdated(Double vacationEarnedFromOutdated) {
        this.vacationEarnedFromOutdated = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationEarnedFromOutdated));
    }

    public Double getOutdateDuration() {
        return outdateDuration;
    }

    public void setOutdateDuration(Double outdateDuration) {
        this.outdateDuration = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(outdateDuration));
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

    public Double getVacationEarnedFromOutdated() {
        return vacationEarnedFromOutdated;
    }

    public Double getOverALLDisposedVacationTaken() {
        return overALLDisposedVacationTaken;
    }

    public void setOverALLDisposedVacationTaken(Double overALLDisposedVacationTaken) {
        this.overALLDisposedVacationTaken = overALLDisposedVacationTaken;
    }

    public Double getOverallTimeOffTaken() {
        return overallTimeOffTaken;
    }

    public void setOverallTimeOffTaken(Double overallTimeOffTaken) {
        this.overallTimeOffTaken = overallTimeOffTaken;
    }

    public VacationConsumptionModel getBalancedVacationConsumption() {
        return balancedVacationConsumption;
    }

    public void setBalancedVacationConsumption(VacationConsumptionModel balancedVacationConsumption) {
        this.balancedVacationConsumption = balancedVacationConsumption;
    }

    public VacationConsumptionModel getDisposedFromOutdatedVacationConsumption() {
        return disposedFromOutdatedVacationConsumption;
    }

    public void setDisposedFromOutdatedVacationConsumption(VacationConsumptionModel disposedFromOutdatedVacationCOnsumption) {
        this.disposedFromOutdatedVacationConsumption = disposedFromOutdatedVacationCOnsumption;
    }

    public VacationConsumptionModel getDisposedFromIndateVacationConsuption() {
        return disposedFromIndateVacationConsuption;
    }

    public void setDisposedFromIndateVacationConsuption(VacationConsumptionModel disposedFromIndateVacationConsuption) {
        this.disposedFromIndateVacationConsuption = disposedFromIndateVacationConsuption;
    }
}
