package com.recruiting.service.employee;


import com.recruiting.domain.CompanyConfig;
import com.recruiting.domain.Employee;
import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;
import com.recruiting.repository.CompanyConfigRepository;
import com.recruiting.repository.EmployeeRepository;
import com.recruiting.repository.IndividualTimeOffRepository;
import com.recruiting.repository.TimeOffTypeRepository;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import com.recruiting.service.vacation.VacationCalculationService;
import com.recruiting.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService{

    @Autowired
    private IndividualTimeOffRepository individualTimeOffRepository;

    @Autowired
    private TimeOffTypeRepository timeOffTypeRepository;

    @Autowired
    private VacationCalculationService vacationCalculationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyConfigRepository companyConfigRepository;



    @Override
    public EmployeeModel getEmployeeModel(Long employeeId) {

        CompanyConfig companyConfig = companyConfigRepository.findFirstByIsActiveTrue();
        Double vacationPerMonth = companyConfig.getVacationPerMonth();
        Double validVacationPeriod = companyConfig.getValidVacationPeriod();
        Employee employee = employeeRepository.findOne(employeeId);
        EmployeeModel model = new EmployeeModel();
        model.setNewIndividualTimeOff(new IndividualTimeOff());
        List<IndividualTimeOff> individualTimeOffs = individualTimeOffRepository.findAllByApprovedTrueAndUser(employee);


        model.setEmployeeId(employeeId);
        model.setEmployeeName(employee.getName());
        model.setJoinDate(employee.getJoiningDate());
        model.setLeaveDate(employee.getLeavingDate());
        model.setTimeOffSummary(getTimeOffSummaryForEmployee(employee));
        model.setNewIndividualTimeOff(new IndividualTimeOff());

        vacationCalculationService.calculateEmployeeVacationData(model, individualTimeOffs, vacationPerMonth, validVacationPeriod);
        return model;
    }


    @Override
    public void saveIndividualTimeOff(IndividualTimeOff individualTimeOffDTO) {

    }

    @Override
    public Map<String, Long> getTimeOffSummaryForEmployee(Employee employee) {

        Map<String, Long> summary = new Hashtable<>();

        List<TimeOffType> timeOffTypes = timeOffTypeRepository.findAll();
        if (timeOffTypes == null) return null;
        timeOffTypes.forEach(timeOffType -> Optional.ofNullable(individualTimeOffRepository.findAllByApprovedTrueAndReasonAndUser(timeOffType, employee))
                .ifPresent(individualTimeOffs -> individualTimeOffs
                        .forEach(individualTimeOff -> {
                            long newValue = DateTimeUtils.calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd());
                            Long oldValue = summary.get(timeOffType.getTitle());
                            if (oldValue != null) {
                                summary.replace(timeOffType.getTitle(), oldValue, (oldValue + newValue));
                            } else {
                                summary.put(timeOffType.getTitle(), newValue);
                            }
                        })));
        return summary;
    }
}
