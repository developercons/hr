package com.recruiting.service.company;

import com.google.common.collect.Lists;
import com.recruiting.domain.CompanyConfig;
import com.recruiting.domain.TimeOffType;
import com.recruiting.domain.WorkingHoursScheme;
import com.recruiting.repository.CompanyConfigRepository;
import com.recruiting.repository.TimeOffTypeRepository;
import com.recruiting.repository.WorkingHoursSchemeRepository;
import com.recruiting.service.company.dto.CompanyConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Marta Ginosyan
 */

@Service("companyFormService")
public class CompanyFormServiceImpl implements CompanyFormService{

    @Autowired
    private CompanyConfigRepository companyConfigRepository;

    @Autowired
    private TimeOffTypeRepository timeOffTypeRepository;

    @Autowired
    private WorkingHoursSchemeRepository workingHoursSchemeRepository;

    @Override
    public CompanyConfigDTO prepareCompany() {
        CompanyConfig companyConfig = companyConfigRepository.findFirstByIsActiveTrue();
        List<TimeOffType> timeOffTypes = timeOffTypeRepository.findAll();
        List<WorkingHoursScheme> workingHoursSchemes = workingHoursSchemeRepository.findAll();
        CompanyConfigDTO companyConfigDTO = new CompanyConfigDTO();
        companyConfigDTO.setTimeoOffTypes(timeOffTypes.toArray(new TimeOffType[timeOffTypes.size()]));
        companyConfigDTO.setWorkingHoursSchemes(workingHoursSchemes.toArray(new WorkingHoursScheme[workingHoursSchemes.size()]));
        companyConfigDTO.setCompanyConfig(companyConfig);
        companyConfigDTO.setWorkingHoursScheme(workingHoursSchemeRepository.findFirstByIsValidTrue());
        return companyConfigDTO;
    }

    @Override
    public CompanyConfigDTO updateCompany(CompanyConfigDTO company) {
        CompanyConfig companyConfig = company.getCompanyConfig();
        companyConfig.setDefaultWorkingHoursScheme(company.getWorkingHoursScheme());
        companyConfigRepository.save(companyConfig);
        timeOffTypeRepository.save(Lists.newArrayList(company.getTimeoOffTypes()));
        workingHoursSchemeRepository.save(Lists.newArrayList(company.getWorkingHoursSchemes()));
        return company;
    }
}
