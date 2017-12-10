package com.recruiting.listener;

import com.recruiting.csv.EmployeeCSVParser;
import com.recruiting.repository.*;
import com.recruiting.utils.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Marta Ginosyan
 */

@Component
public class ApplicationEventListener {

    private static boolean DATA_LOADED = false;

    private UserRepository userRepository;
    private CompanyConfigRepository companyConfigRepository;
    private TimeOffTypeRepository timeOffTypeRepository;
    private AuthorityRepository authorityRepository;
    private WorkingHoursSchemeRepository workingHoursSchemeRepository;
    private EmployeeCSVParser employeeCSVParser;
    private EmployeeRepository employeeRepository;
    private IndividualTimeOffRepository individualTimeOffRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setCompanyConfigRepository(CompanyConfigRepository companyConfigRepository) {
        this.companyConfigRepository = companyConfigRepository;
    }

    @Autowired
    public void setTimeOffTypeRepository(TimeOffTypeRepository timeOffTypeRepository) {
        this.timeOffTypeRepository = timeOffTypeRepository;
    }

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Autowired
    public void setWorkingHoursSchemeRepository(WorkingHoursSchemeRepository workingHoursSchemeRepository) {
        this.workingHoursSchemeRepository = workingHoursSchemeRepository;
    }

    @Autowired
    public void setEmployeeCSVParser(EmployeeCSVParser employeeCSVParser) {
        this.employeeCSVParser = employeeCSVParser;
    }

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Autowired
    public void setIndividualTimeOffRepository(IndividualTimeOffRepository individualTimeOffRepository) {
        this.individualTimeOffRepository = individualTimeOffRepository;
    }

    //    @Autowired
//    public ApplicationEventListener(UserRepository userRepository, CompanyConfigRepository companyConfigRepository, TimeOffTypeRepository timeOffTypeRepository, AuthorityRepository authorityRepository, WorkingHoursSchemeRepository workingHoursSchemeRepository, EmployeeCSVParser employeeCSVParser) {
//        this.userRepository = userRepository;
//        this.companyConfigRepository = companyConfigRepository;
//        this.timeOffTypeRepository = timeOffTypeRepository;
//        this.authorityRepository = authorityRepository;
//        this.workingHoursSchemeRepository = workingHoursSchemeRepository;
//        this.employeeCSVParser = employeeCSVParser;
//    }

    @EventListener({ContextRefreshedEvent.class})
    public void onContextRefreshedEvent() {
        if (DATA_LOADED) {
            return;
        }
        DataLoader.createCompanyData(companyConfigRepository, timeOffTypeRepository, workingHoursSchemeRepository);
        DataLoader.createUserData(userRepository, authorityRepository, workingHoursSchemeRepository);
        DataLoader.loadIndividualTimeOffs(employeeCSVParser);
        DataLoader.createNonApprovedIndividualTimeOffs(employeeRepository, individualTimeOffRepository, timeOffTypeRepository);
        DATA_LOADED = true;

    }
}
