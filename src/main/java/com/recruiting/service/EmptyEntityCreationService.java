package com.recruiting.service;

import com.recruiting.domain.Employee;
import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.PasswordResetToken;

public interface EmptyEntityCreationService {

    Employee createEmptyEmployee();
    IndividualTimeOff createEmptyIndividualTimeOff();
    PasswordResetToken emptyPasswordResetToken();

}
