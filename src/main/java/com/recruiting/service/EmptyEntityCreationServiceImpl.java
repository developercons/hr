package com.recruiting.service;

import com.recruiting.domain.Employee;
import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.PasswordResetToken;
import com.recruiting.repository.AuthorityRepository;
import com.recruiting.repository.WorkingHoursSchemeRepository;
import com.recruiting.utils.BusKeyGen;
import com.recruiting.utils.Constants;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmptyEntityCreationServiceImpl implements EmptyEntityCreationService {

    @Autowired
    WorkingHoursSchemeRepository workingHoursSchemeRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Employee createEmptyEmployee() {
        Employee employee = new Employee();
        employee.setAccountNonExpired(true);
        employee.setAccountNonLocked(true);
        employee.setCredentialsNonExpired(true);
        employee.setEnabled(true);
        employee.setIndividualWorkingHoursScheme(workingHoursSchemeRepository.findFirstByIsValidTrue());
        employee.setGrantedAuthorities(Lists.newArrayList(authorityRepository.findByRole("EMPLOYEE")));

        return employee;
    }

    @Override
    public IndividualTimeOff createEmptyIndividualTimeOff() {
        IndividualTimeOff individualTimeOff = new IndividualTimeOff();
        individualTimeOff.setDisposed(false);
        return individualTimeOff;
    }

    @Override
    public PasswordResetToken emptyPasswordResetToken() {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationDate = now.plusMinutes(Constants.PASSWORD_RESET_TOKEN_EXPIRATION_MINUTES);
        passwordResetToken.setExpiryDate(expirationDate);
        passwordResetToken.setToken(BusKeyGen.nextKey());
        return passwordResetToken;
    }
}
