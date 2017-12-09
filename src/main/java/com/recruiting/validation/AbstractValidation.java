package com.recruiting.validation;

import com.recruiting.domain.TimeOffType;
import com.recruiting.utils.StringUtils;
import com.recruiting.utils.ValidationPatternUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.webflow.execution.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Martha on 5/14/2017.
 */
@PropertySource("classpath:validation.properties")
public class AbstractValidation {

    @Value("${city.lenght}")
    protected int city_lenght;

    @Value("${address.length}")
    protected int address_length;

    @Value("${email.lenght}")
    protected int email_lenght;

    @Value("${full.name.length}")
    protected int full_name_length;

    @Value("${company.name.length}")
    protected int company_name_length;

    public void validateAuthenticationCredentials(boolean agreed, String initialUsername, String username, String password, String verifyPassword, String initialFieldName, boolean isSignedInUser, String newPassword, Errors errors) {
        if (isSignedInUser) {
            if (!initialUsername.equals(username)) validateUsername(username, initialFieldName, errors);
        } else validateUsername(username, initialFieldName, errors);

        if (isSignedInUser && !newPassword.equals(password)) {
            if (!StringUtils.isNullOrEmpty(newPassword))
                validatePassword(newPassword, "", verifyPassword, ".newPassword", errors);
        } else validatePassword(password, initialFieldName, verifyPassword, ".password", errors);

        if (!isSignedInUser) validateAgreed(agreed, errors);
    }

    private void validateUsername(String username, String initialFieldName, Errors errors) {
        if (!StringUtils.isNullOrEmpty(username) && username.length() > email_lenght)
            errors.rejectValue(initialFieldName + ".username", "length.restriction.email");
        validateRequiredByPatternNonEmpty(username,
                ValidationPatternUtils.EMAIL_PATTERN_DEVELOPMENT,
                initialFieldName + ".username",
                "invalid.email",
                errors);
    }

    private void validatePassword(String password, String initialFieldName, String verifyPassword, String fieldName, Errors errors) {
        if (!StringUtils.isNullOrEmpty(password) && password.length() > email_lenght)
            errors.rejectValue(initialFieldName + fieldName, "length.restriction.password");
        validateRequiredByPatternNonEmpty(password,
                ValidationPatternUtils.PASSWORD_PATTERN, initialFieldName + fieldName, "invalid.password", errors);

        if (StringUtils.isNullOrEmpty(verifyPassword))
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "verifyPassword", "required.verifyPassword");
        else validateVerifyPassword(password, verifyPassword, "verifyPassword", errors);
    }

    public void validateByPattern(String input, String pattern, String fieldName, String errorCode, Errors errors) {
        if (StringUtils.isNullOrEmpty(input)) return;
        Pattern patternEmail = Pattern.compile(pattern);
        Matcher matcher = patternEmail.matcher(input);
        if (!matcher.matches()) {
            errors.rejectValue(fieldName, errorCode);
        }
    }

    public void validateRequiredByPatternNonEmpty(String input, String pattern, String fieldName, String errorCode, Errors errors) {
        if (StringUtils.isNullOrEmpty(input)) {
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, fieldName, errorCode);
            return;
        }
        Pattern patternEmail = Pattern.compile(pattern);
        Matcher matcher = patternEmail.matcher(input);
        if (!matcher.matches()) {
            errors.rejectValue(fieldName, errorCode);
        }
    }

    public void validateEmptySelectionValue(String selecctedValue, Errors errors, String field, String errorCode) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode);
        if (selecctedValue.equals("0")) errors.rejectValue(field, errorCode);
    }

    public void validateEquality(String originalValue, String givenValue, String fieldName, Errors errors) {
        if (!originalValue.equals(givenValue)) {
            errors.rejectValue(fieldName, "no.match.password");
        }
    }

    public void validateBooleanIsTrue(boolean value, String field, String errorCode, Errors errors) {
        if (!value) {
            errors.rejectValue(field, errorCode);
        }
    }

    public void validateVerifyPassword(String originalPassword, String verifyPassword, String fieldName, Errors errors) {
        if (!originalPassword.equals(verifyPassword)) {
            errors.rejectValue(fieldName, "no.match.password");
        }
    }

    public void validateAgreed(boolean agreed, Errors errors) {
        if (!agreed) {
            errors.rejectValue("agreed", "agree.with.terms");
        }
    }

    protected void validateIndividualTimeOff(TimeOffType timeOffType, String timeOffTypeFieldName, Errors errors) {
        if (timeOffType == null || timeOffType.getTitle() == null) {
            errors.rejectValue(timeOffTypeFieldName, null, "Time-off type is required.");
            return;
        }
        String title = timeOffType.getTitle();
        if (StringUtils.isNullOrEmpty(title)) errors.rejectValue(timeOffTypeFieldName, "time.off.type.required");
        else validateEmptySelectionValue(title, errors, timeOffTypeFieldName, "time.off.type.required");
    }

    protected void validateDateIsAfterNow(LocalDateTime localDateTime, String fieldName, Errors errors){
        if(localDateTime.isBefore(LocalDateTime.now())){
            errors.rejectValue(fieldName, "invalid.date");
        }
    }

    protected String resolveTransitionId() {
        return RequestContextHolder.getRequestContext().getCurrentView().getUserEventState().toString().split("eventId = ")[1].split("mappingResults")[0].replaceAll("[^a-zA-Z]", "");
    }

    protected String resolveStateId() {
        return RequestContextHolder.getRequestContext().getFlowExecutionContext().getActiveSession().getState().getId();
    }
}
