package com.recruiting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */
@Entity
@Table(name = "time_off_type")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeOffType extends AbstractTitledDomain implements Serializable, Titled {

    @Column(name = "is_paid", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isPaid;

    @Column(name = "disposable_from_vacation", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean disposableFromVacation;

    @Column(name = "is_valid", columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean isValid;

    public TimeOffType() {
    }

    public TimeOffType(String ssn, String title, Boolean isPaid, Boolean disposableFromVacation, Boolean isValid) {
        super(ssn, title);
        this.isPaid = isPaid;
        this.disposableFromVacation = disposableFromVacation;
        this.isValid = isValid;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean getDisposableFromVacation() {
        return disposableFromVacation;
    }

    public void setDisposableFromVacation(Boolean disposableFromVacation) {
        this.disposableFromVacation = disposableFromVacation;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
