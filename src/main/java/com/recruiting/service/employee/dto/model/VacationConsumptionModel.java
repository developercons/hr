package com.recruiting.service.employee.dto.model;

import com.recruiting.utils.ValidationPatternUtils;

public class VacationConsumptionModel {

    private Double vacationTakenFromIndate;
    private Double vacationLeftFromIndate;
    private Double vacationInAdvanceFromIndate;
    private Double timeOffFromIndate;

    private Double vacationTakenFromOutDate;
    private Double vacationLeftFromOutdate;
    private Double vacationInAdvanceFromOutDate;
    private Double timeOffFromOutdate;

    public Double getVacationTakenFromIndate() {
        return vacationTakenFromIndate;
    }

    public void setVacationTakenFromIndate(Double vacationTakenFromIndate) {
        this.vacationTakenFromIndate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationTakenFromIndate));
    }

    public Double getVacationLeftFromIndate() {
        return vacationLeftFromIndate;
    }

    public void setVacationLeftFromIndate(Double vacationLeftFromIndate) {
        this.vacationLeftFromIndate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationLeftFromIndate));
    }

    public Double getVacationInAdvanceFromIndate() {
        return vacationInAdvanceFromIndate;
    }

    public void setVacationInAdvanceFromIndate(Double vacationInAdvanceFromIndate) {
        this.vacationInAdvanceFromIndate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationInAdvanceFromIndate));
    }

    public Double getTimeOffFromIndate() {
        return timeOffFromIndate;
    }

    public void setTimeOffFromIndate(Double timeOffFromIndate) {
        this.timeOffFromIndate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(timeOffFromIndate));
    }

    public Double getVacationTakenFromOutDate() {
        return vacationTakenFromOutDate;
    }

    public void setVacationTakenFromOutDate(Double vacationTakenFromOutDate) {
        this.vacationTakenFromOutDate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationTakenFromOutDate));
    }

    public Double getVacationLeftFromOutdate() {
        return vacationLeftFromOutdate;
    }

    public void setVacationLeftFromOutdate(Double vacationLeftFromOutdated) {
        this.vacationLeftFromOutdate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationLeftFromOutdated));
    }

    public Double getVacationInAdvanceFromOutDate() {
        return vacationInAdvanceFromOutDate;
    }

    public void setVacationInAdvanceFromOutDate(Double vacationInAdvanceFromOutDate) {
        this.vacationInAdvanceFromOutDate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(vacationInAdvanceFromOutDate));
    }

    public Double getTimeOffFromOutdate() {
        return timeOffFromOutdate;
    }

    public void setTimeOffFromOutdate(Double timeOffFromOutdate) {
        this.timeOffFromOutdate = Double.parseDouble(ValidationPatternUtils.decimalFormat.format(timeOffFromOutdate));
    }
}
