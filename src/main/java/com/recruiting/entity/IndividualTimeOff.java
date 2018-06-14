package com.recruiting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recruiting.converter.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@Entity
@Table(name = "individual_time_off")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndividualTimeOff extends AbstractDomain implements Serializable {

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "start")
    private LocalDateTime start;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "end")
    private LocalDateTime end;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "start_hr")
    private LocalDateTime start_hr;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "end_hr")
    private LocalDateTime end_hr;

    @OneToOne
    private TimeOffType reason;

    @OneToOne
    private User user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "disposed", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean disposed;

    @Column(name = "disposed_hr", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean disposed_hr;

    @Column(name = "approved", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean approved;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<User> approvers;

    public IndividualTimeOff() {
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public LocalDateTime getStart_hr() {
        return start_hr;
    }

    public void setStart_hr(LocalDateTime start_hr) {
        this.start_hr = start_hr;
    }

    public LocalDateTime getEnd_hr() {
        return end_hr;
    }

    public void setEnd_hr(LocalDateTime end_hr) {
        this.end_hr = end_hr;
    }

    public TimeOffType getReason() {
        return reason;
    }

    public void setReason(TimeOffType reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDisposed() {
        return disposed;
    }

    public void setDisposed(Boolean disposed) {
        this.disposed = disposed;
    }

    public Boolean getDisposed_hr() {
        return disposed_hr;
    }

    public void setDisposed_hr(Boolean disposed_hr) {
        this.disposed_hr = disposed_hr;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public List<User> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<User> approvers) {
        this.approvers = approvers;
    }
}
