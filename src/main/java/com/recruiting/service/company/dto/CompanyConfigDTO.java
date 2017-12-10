package com.recruiting.service.company.dto;

import com.recruiting.domain.CompanyConfig;
import com.recruiting.domain.TimeOffType;
import com.recruiting.domain.WorkingHoursScheme;

import java.io.Serializable;

/**
 * @author Marta Ginosyan
 */

public class CompanyConfigDTO implements Serializable{

    private TimeOffType[] timeoOffTypes;
    private WorkingHoursScheme[] workingHoursSchemes;
    private CompanyConfig companyConfig;
    private WorkingHoursScheme workingHoursScheme;

    public TimeOffType[] getTimeoOffTypes() {
        return timeoOffTypes;
    }

    public void setTimeoOffTypes(TimeOffType[] timeoOffTypes) {
        this.timeoOffTypes = timeoOffTypes;
    }

    public WorkingHoursScheme[] getWorkingHoursSchemes() {
        return workingHoursSchemes;
    }

    public void setWorkingHoursSchemes(WorkingHoursScheme[] workingHoursSchemes) {
        this.workingHoursSchemes = workingHoursSchemes;
    }

    public CompanyConfig getCompanyConfig() {
        return companyConfig;
    }

    public void setCompanyConfig(CompanyConfig companyConfig) {
        this.companyConfig = companyConfig;
    }

    public WorkingHoursScheme getWorkingHoursScheme() {
        return workingHoursScheme;
    }

    public void setWorkingHoursScheme(WorkingHoursScheme workingHoursScheme) {
        this.workingHoursScheme = workingHoursScheme;
    }
}
