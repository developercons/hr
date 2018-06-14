package com.recruiting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marta Ginosyan
 */

@Entity
@Table(name = "employee")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee extends User implements Serializable {

    @OneToOne
    private WorkingHoursScheme individualWorkingHoursScheme;

    @Column(name = "job_title")
    private String jobTitle;


    @Column(name = "leaved", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean leaved;


    public Employee() {
    }

    public Employee(String ssn, String username, String password, String name, String phone, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, Boolean approved, LocalDateTime joiningDate, LocalDateTime leavingDate, List<Authority> grantedAuthorities, WorkingHoursScheme individualWorkingHoursScheme, Boolean leaved) {
        super(ssn, username, password, name, phone, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, approved, joiningDate, leavingDate, grantedAuthorities);
        this.individualWorkingHoursScheme = individualWorkingHoursScheme;
        this.leaved = leaved;
    }

    public WorkingHoursScheme getIndividualWorkingHoursScheme() {
        return individualWorkingHoursScheme;
    }

    public void setIndividualWorkingHoursScheme(WorkingHoursScheme individualWorkingHoursScheme) {
        this.individualWorkingHoursScheme = individualWorkingHoursScheme;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Boolean getLeaved() {
        return leaved;
    }

    public void setLeaved(Boolean leaved) {
        this.leaved = leaved;
    }
}
