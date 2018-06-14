package com.recruiting.service.employee.dto.model;

import com.recruiting.entity.IndividualTimeOff;
import com.recruiting.utils.ValidationPatternUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Marta Ginosyan
 */

public class EmployeeDetailsModel extends EmployeeModel implements Serializable {


    private Map<String, Long> timeOffSummary;
    private IndividualTimeOff newIndividualTimeOff;
    private List<IndividualTimeOff> acceptedIndividualTimeOffs;
    private List<IndividualTimeOff> rejectedIndividualTimeOffs;
    private List<IndividualTimeOff> pendingTimeOffs;

    private Double vacationEarnedFromIndate;
    private Double indateDuration;
    private Double vacationEarnedFromOutdated;
    private Double outdateDuration;

    private Double overallTimeOffTaken;

    private VacationConsumptionModel balancedVacationConsumption;
    private VacationConsumptionModel disposedFromOutdatedVacationConsumption;
    private VacationConsumptionModel disposedFromIndateVacationConsuption;


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

    public Double getVacationEarnedFromOutdated() {
        return vacationEarnedFromOutdated;
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
