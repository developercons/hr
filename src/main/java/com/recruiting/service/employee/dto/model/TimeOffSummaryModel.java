package com.recruiting.service.employee.dto.model;

import java.io.Serializable;

public class TimeOffSummaryModel implements Serializable {

    private String title;
    private Long overall;
    private Long disposed;
    private Long notDisposed;
    private Long disposedHR;
    private Boolean disposable;

    public TimeOffSummaryModel() {
        overall = 0L;
        disposed = 0L;
        notDisposed = 0L;
        disposedHR = 0L;
    }

    public Long getOverall() {
        return overall;
    }

    public void setOverall(Long overall) {
        this.overall = overall;
    }

    public Long getDisposed() {
        return disposed;
    }

    public void setDisposed(Long disposed) {
        this.disposed = disposed;
    }

    public Long getNotDisposed() {
        return notDisposed;
    }

    public void setNotDisposed(Long notDisposed) {
        this.notDisposed = notDisposed;
    }

    public Long getDisposedHR() {
        return disposedHR;
    }

    public void setDisposedHR(Long disposedHR) {
        this.disposedHR = disposedHR;
    }

    public Boolean getDisposable() {
        return disposable;
    }

    public void setDisposable(Boolean disposable) {
        this.disposable = disposable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
