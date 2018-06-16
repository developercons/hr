package com.recruiting.service.employee;


import com.recruiting.domain.CompanyConfig;
import com.recruiting.domain.Employee;
import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;
import com.recruiting.model.modelUtils.PageWrapper;
import com.recruiting.repository.CompanyConfigRepository;
import com.recruiting.repository.EmployeeRepository;
import com.recruiting.repository.IndividualTimeOffRepository;
import com.recruiting.repository.TimeOffTypeRepository;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeFullDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import com.recruiting.service.vacation.VacationCalculationService;
import com.recruiting.utils.DateTimeUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Marta Ginosyan
 */

@Service
public class EmployeeDetailServiceImpl implements EmployeeDetailService {

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
    public PageWrapper<IndividualTimeOff> getTimeOffsByEmployee(String id, Pageable pageable) {
        Employee employee = employeeRepository.findOne(id);
        Sort.Order start = new Sort.Order(Sort.Direction.DESC, "start");
        Sort sort = new Sort(Lists.newArrayList(start));
        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<IndividualTimeOff> timeOffs = individualTimeOffRepository.findAllByUser(employee, pageRequest);
        if (!timeOffs.hasContent()) {
            return new PageWrapper<>();
        }
        PageWrapper<IndividualTimeOff> pageWrapper = new PageWrapper<>(timeOffs, "");
        return pageWrapper;
    }


    @Override
    public EmployeeModel getEmployeeModel(final String employeeId) {
        CompanyConfig companyConfig = companyConfigRepository.findFirstByIsActiveTrue();
        Double vacationPerMonth = companyConfig.getVacationPerMonth();
        Double validVacationPeriod = companyConfig.getValidVacationPeriod();
        Employee employee = employeeRepository.findOne(employeeId);
        EmployeeModel model = new EmployeeModel();
        model.setEmployeeId(employeeId);
        model.setEmployeeName(employee.getName());
        model.setJoinDate(employee.getJoiningDate());
        model.setLeaveDate(employee.getLeavingDate());
        List<IndividualTimeOff> individualTimeOffs = individualTimeOffRepository.findAllByApprovedTrueAndUser(employee);
        vacationCalculationService.calculateEmployeeModelData(model, individualTimeOffs, vacationPerMonth, validVacationPeriod);
        return model;
    }

    @Override
    public EmployeeDetailsModel getEmployeeDetailsModel(String employeeId) {

        CompanyConfig companyConfig = companyConfigRepository.findFirstByIsActiveTrue();
        Double vacationPerMonth = companyConfig.getVacationPerMonth();
        Double validVacationPeriod = companyConfig.getValidVacationPeriod();
        Employee employee = employeeRepository.findOne(employeeId);
        EmployeeDetailsModel model = new EmployeeDetailsModel();
        model.setNewIndividualTimeOff(new IndividualTimeOff());
        List<IndividualTimeOff> individualTimeOffs = individualTimeOffRepository.findAllByApprovedTrueAndUser(employee);


        model.setEmployeeId(employeeId);
        model.setEmployeeName(employee.getName());
        model.setJoinDate(employee.getJoiningDate());
        model.setLeaveDate(employee.getLeavingDate());
        model.setTimeOffSummary(getTimeOffSummaryForEmployee(employee));
        model.setNewIndividualTimeOff(new IndividualTimeOff());

        vacationCalculationService.calculateEmployeeDetailsVacationData(model, individualTimeOffs, vacationPerMonth, validVacationPeriod);
        return model;
    }

    @Override
    public EmployeeFullDetailsModel getEmployeeFullDetailsModel(String employeeId) {
        return null;
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

    @Override
    public Map<String, Long> getTimeOffSummaryForEmployee(String id) {
        return getTimeOffSummaryForEmployee(employeeRepository.findOne(id));
    }
}
