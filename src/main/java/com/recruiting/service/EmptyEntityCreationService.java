package com.recruiting.service;

import com.recruiting.entity.Employee;
import com.recruiting.entity.IndividualTimeOff;
import com.recruiting.entity.PasswordResetToken;

/**
 * @author Marta Ginosyan
 */

public interface EmptyEntityCreationService {

    Employee createEmptyEmployee();
    IndividualTimeOff createEmptyIndividualTimeOff();
    PasswordResetToken emptyPasswordResetToken();

}
