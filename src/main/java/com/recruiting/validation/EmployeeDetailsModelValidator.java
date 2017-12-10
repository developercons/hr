package com.recruiting.validation;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

/**
 * @author Marta Ginosyan
 */

@Component
public class EmployeeDetailsModelValidator extends AbstractValidation implements Validator {

    public boolean supports(Class clazz) {
        return clazz.equals(EmployeeDetailsModel.class);
    }

    public void validate(Object object, Errors errors) {
        EmployeeDetailsModel employeeDetailsModel = (EmployeeDetailsModel) object;
        IndividualTimeOff individualTimeOff = employeeDetailsModel.getNewIndividualTimeOff();
        LocalDateTime startDate = individualTimeOff.getStart();
        LocalDateTime endDate = individualTimeOff.getEnd();
        if (startDate == null) {
            ValidationUtils.rejectIfEmpty(errors, "newIndividualTimeOff.start", "invalid.date");
            return;
        } else {
            startDate = startDate.plusHours(10);
            validateDateIsAfterNow(individualTimeOff.getStart(), "newIndividualTimeOff.start", errors);
        }

        if (endDate == null) {
            ValidationUtils.rejectIfEmpty(errors, "newIndividualTimeOff.end", "invalid.date");
            return;
        } else {
            endDate = endDate.plusHours(19);
            validateDateIsAfterNow(individualTimeOff.getEnd(), "newIndividualTimeOff.end", errors);
        }

        validateIndividualTimeOff(individualTimeOff.getReason(), "newIndividualTimeOff.reason", errors);
    }
}
