package com.recruiting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recruiting.converter.LocalTimeAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * @author Marta Ginosyan
 */

@Entity
@Table(name = "company_config")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyConfig extends AbstractDomain implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "dispose_type_employee")
    private String vacationDisposeTypeForEmployee;

    @Column(name = "dispose_type_hr")
    private String vacationDisposeTypeForHR;

    @OneToOne
    private WorkingHoursScheme defaultWorkingHoursScheme;

    @Column(name = "vacation_per_month")
    private Double vacationPerMonth;

    @Column(name = "valid_vacation_period")
    private Double validVacationPeriod;

    @Convert(converter = LocalTimeAttributeConverter.class)
    @Column(name = "lunch_start")
    private LocalTime lunchStartTime;

    @Column(name = "lunch_duration")
    private Double lunchDuration;

    @Column(name = "vacation_in_advance", columnDefinition = "BOOLEAN DEFAULT TRUE", nullable = false)
    private Boolean vacationInAdvanceAllowed;

    @Column(name = "half_day_off", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private Boolean halfDayTimeOffAllowed;

    @Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private Boolean isActive;

    public CompanyConfig() {
    }

    public CompanyConfig(String ssn, String name, String email, String vacationDisposeTypeForEmployee, String vacationDisposeTypeForHR, WorkingHoursScheme defaultWorkingHoursScheme, Double vacationPerMonth, Double validVacationPeriod, LocalTime lunchStartTime, Double lunchDuration, Boolean vacationInAdvanceAllowed, Boolean halfDayTimeOffAllowed, Boolean isActive) {
        super(ssn);
        this.name = name;
        this.email = email;
        this.vacationDisposeTypeForEmployee = vacationDisposeTypeForEmployee;
        this.vacationDisposeTypeForHR = vacationDisposeTypeForHR;
        this.defaultWorkingHoursScheme = defaultWorkingHoursScheme;
        this.vacationPerMonth = vacationPerMonth;
        this.validVacationPeriod = validVacationPeriod;
        this.lunchStartTime = lunchStartTime;
        this.lunchDuration = lunchDuration;
        this.vacationInAdvanceAllowed = vacationInAdvanceAllowed;
        this.halfDayTimeOffAllowed = halfDayTimeOffAllowed;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVacationDisposeTypeForEmployee() {
        return vacationDisposeTypeForEmployee;
    }

    public void setVacationDisposeTypeForEmployee(String vacationDisposeTypeForEmployee) {
        this.vacationDisposeTypeForEmployee = vacationDisposeTypeForEmployee;
    }

    public String getVacationDisposeTypeForHR() {
        return vacationDisposeTypeForHR;
    }

    public void setVacationDisposeTypeForHR(String vacationDisposeTypeForHR) {
        this.vacationDisposeTypeForHR = vacationDisposeTypeForHR;
    }

    public WorkingHoursScheme getDefaultWorkingHoursScheme() {
        return defaultWorkingHoursScheme;
    }

    public void setDefaultWorkingHoursScheme(WorkingHoursScheme defaultWorkingHoursScheme) {
        this.defaultWorkingHoursScheme = defaultWorkingHoursScheme;
    }

    public Double getVacationPerMonth() {
        return vacationPerMonth;
    }

    public void setVacationPerMonth(Double vacationPerMonth) {
        this.vacationPerMonth = vacationPerMonth;
    }

    public Double getValidVacationPeriod() {
        return validVacationPeriod;
    }

    public void setValidVacationPeriod(Double validVacationPeriod) {
        this.validVacationPeriod = validVacationPeriod;
    }

    public LocalTime getLunchStartTime() {
        return lunchStartTime;
    }

    public void setLunchStartTime(LocalTime lunchStartTime) {
        this.lunchStartTime = lunchStartTime;
    }

    public Double getLunchDuration() {
        return lunchDuration;
    }

    public void setLunchDuration(Double lunchDuration) {
        this.lunchDuration = lunchDuration;
    }

    public Boolean getVacationInAdvanceAllowed() {
        return vacationInAdvanceAllowed;
    }

    public void setVacationInAdvanceAllowed(Boolean vacationInAdvanceAllowed) {
        this.vacationInAdvanceAllowed = vacationInAdvanceAllowed;
    }

    public Boolean getHalfDayTimeOffAllowed() {
        return halfDayTimeOffAllowed;
    }

    public void setHalfDayTimeOffAllowed(Boolean halfDayTimeOffAllowed) {
        this.halfDayTimeOffAllowed = halfDayTimeOffAllowed;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
