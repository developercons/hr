package com.recruiting.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recruiting.converter.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "persistent_logins")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersistentLogins {

    @Column(name = "username")
    private String username;

    @Id
    @Column(name = "series")
    private String series;

    @Column(name = "token")
    private String token;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(name = "last_used")
    private LocalDateTime last_used;

    public PersistentLogins() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getLast_used() {
        return last_used;
    }

    public void setLast_used(LocalDateTime last_used) {
        this.last_used = last_used;
    }
}
