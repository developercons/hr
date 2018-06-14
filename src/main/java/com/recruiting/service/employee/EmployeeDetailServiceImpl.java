package com.recruiting.service.employee;


import com.recruiting.entity.CompanyConfig;
import com.recruiting.entity.Employee;
import com.recruiting.entity.IndividualTimeOff;
import com.recruiting.entity.TimeOffType;
import com.recruiting.model.modelUtils.PageWrapper;
import com.recruiting.repository.CompanyConfigRepository;
import com.recruiting.repository.EmployeeRepository;
import com.recruiting.repository.IndividualTimeOffRepository;
import com.recruiting.repository.TimeOffTypeRepository;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeFullDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import com.recruiting.service.employee.dto.model.TimeOffSummaryModel;
import com.recruiting.service.vacation.VacationCalculationService;
import com.recruiting.utils.DateTimeUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    public PageWrapper<IndividualTimeOff> getTimeOffsByEmployee(Long id, Pageable pageable) {
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
    public EmployeeModel getEmployeeModel(Long employeeId) {
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
    public EmployeeDetailsModel getEmployeeDetailsModel(Long employeeId) {

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
        model.setTimeOffSummary(getTimeOffSummaryMapForEmployee(employee));
        model.setNewIndividualTimeOff(new IndividualTimeOff());

        vacationCalculationService.calculateEmployeeDetailsVacationData(model, individualTimeOffs, vacationPerMonth, validVacationPeriod);
        return model;
    }

    @Override
    public EmployeeFullDetailsModel getEmployeeFullDetailsModel(Long employeeId) {
        return null;
    }

    @Override
    public Map<String, Long> getTimeOffSummaryMapForEmployee(Employee employee) {

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
    public Map<String, TimeOffSummaryModel> getTimeOffSummaryForEmployee(Employee employee) {

        Map<String, TimeOffSummaryModel> summary = new Hashtable<>();

        List<TimeOffType> timeOffTypes = timeOffTypeRepository.findAll();
        if (timeOffTypes == null) return null;
        timeOffTypes.forEach(timeOffType -> Optional.ofNullable(individualTimeOffRepository.findAllByApprovedTrueAndReasonAndUser(timeOffType, employee))
                .ifPresent(individualTimeOffs -> individualTimeOffs
                        .forEach(individualTimeOff -> {
                            TimeOffSummaryModel timeOffSummaryModel = summary.get(timeOffType.getTitle());
                            if (timeOffSummaryModel == null){
                                timeOffSummaryModel = new TimeOffSummaryModel();
                                summary.put(timeOffType.getTitle(), timeOffSummaryModel);
                                timeOffSummaryModel.setDisposable(individualTimeOff.getReason().getDisposableFromVacation());
                                timeOffSummaryModel.setTitle(individualTimeOff.getReason().getTitle());
                            }
                            long days = DateTimeUtils.calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd());
                            long oldOverall = timeOffSummaryModel.getOverall();
                            long newOverall = oldOverall + days;
                            long oldDisposed = timeOffSummaryModel.getDisposed();
                            long newDisposed = oldDisposed + (individualTimeOff.getDisposed() ? days : 0);
                            long oldNotDisposed = timeOffSummaryModel.getNotDisposed();
                            long newNotDisposed = oldNotDisposed + (!individualTimeOff.getDisposed() ? days : 0);
                            long oldDisposedHR = timeOffSummaryModel.getDisposedHR();
                            long newDisposedHR = oldDisposedHR + (individualTimeOff.getDisposed_hr() ? days : 0);
                            timeOffSummaryModel.setOverall(newOverall);
                            timeOffSummaryModel.setDisposed(newDisposed);
                            timeOffSummaryModel.setNotDisposed(newNotDisposed);
                            timeOffSummaryModel.setDisposedHR(newDisposedHR);
                            summary.replace(timeOffType.getTitle(), timeOffSummaryModel);
                        })));
        return summary;
    }

    @Override
    public Map<String, TimeOffSummaryModel> getTimeOffSummaryForEmployee(Long id) {
        return getTimeOffSummaryForEmployee(employeeRepository.findOne(id));
    }

    @Override
    public PageWrapper<TimeOffSummaryModel> getTimeOffSummaryForEmployee(Long id, Pageable pageable) {
        Map<String, TimeOffSummaryModel> timeOffSummaryModelMap = getTimeOffSummaryForEmployee(employeeRepository.findOne(id));
        if (timeOffSummaryModelMap.isEmpty()) {
            return new PageWrapper<>();
        }
        Page<TimeOffSummaryModel> page = new PageImpl<>(Lists.newArrayList(timeOffSummaryModelMap.values()), pageable, 3);
        PageWrapper<TimeOffSummaryModel> pageWrapper = new PageWrapper<>(page, "");
        return pageWrapper;
    }
}
