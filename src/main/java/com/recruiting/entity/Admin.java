package com.recruiting.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Marta Ginosyan
 */

@Entity
@Table(name = "admin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin extends User implements Serializable {

    public Admin() {
    }

    public Admin(String ssn, String username, String password, String name, String phone, Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, Boolean approved, LocalDateTime joiningDate, LocalDateTime leavingDate, List<Authority> grantedAuthorities) {
        super(ssn, username, password, name, phone, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled, approved, joiningDate, leavingDate, grantedAuthorities);
    }
}
