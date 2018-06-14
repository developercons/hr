package com.recruiting.service.vacation;

import com.recruiting.entity.IndividualTimeOff;
import com.recruiting.service.employee.dto.model.EmployeeDetailsModel;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import com.recruiting.service.employee.dto.model.VacationConsumptionModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Marta Ginosyan
 */

public interface VacationCalculationService {

    <T extends EmployeeModel> void calculateEmployeeModelData(T model, List<IndividualTimeOff> timeOffs, Double vacationPerMonth, Double validVacationPeriod);

    <T extends EmployeeDetailsModel> void calculateEmployeeDetailsVacationData(T model, List<IndividualTimeOff> timeOffs, Double vacationPerMonth, Double validVacationPeriod);

    VacationConsumptionModel calculateBalancedVacationConsumption(
            List<IndividualTimeOff> timeOffs,
            Double vacationEarnedFromIndate,
            Double VacationEarnedFromOutdate,
            LocalDateTime indatePeriodStart,
            LocalDateTime indatePeriodEnd,
            LocalDateTime outDatePeriodStart,
            LocalDateTime outdatePeriodEnd);

    VacationConsumptionModel calcualteDisposedFromOutdateVacationConsuption(
            Double overallDisposedTimeOff,
            Double overallTimeOff,
            Double vacationGrantedFromOutDate,
            Double vacationGrantedFromInDate);

    VacationConsumptionModel calcualteDisposedFromIndateVacationConsuption(
            Double overallDisposedTimeOff,
            Double overallTimeOff,
            Double vacationGrantedFromOutDate,
            Double vacationGrantedFromInDate);

    void setIndateVacationDebt(VacationConsumptionModel model, Double vacationEarnedFromIndate);

    void setOutdateVacationDebt(VacationConsumptionModel model, Double vacationEarnedFromOutdate);

    Map<String, Long> summarizeDisposedTimeOff(List<IndividualTimeOff> timeOffs);

    Double calculateOverallVacationFromOutdated(
            LocalDateTime joinDate,
            LocalDateTime endDate,
            Double vacationPerMonth,
            Double validVacationPeriod);

    Double calculateOverallVacationFromIndate(
            LocalDateTime joinDate,
            LocalDateTime endDate,
            Double vacationPerMonth,
            Double validVacationPeriod);

    Double calculateOverallVacationGranted(
            LocalDateTime joinDate,
            LocalDateTime endDate,
            Double vacationPerMonth,
            Double validVacationPeriod);

    Double calculateOverAllDisposableTimeOffTaken(List<IndividualTimeOff> individualTimeOffs);

    Double calculateOverAllDisposedVacationTaken(List<IndividualTimeOff> individualTimeOffs);

    Double calculateOverAllTimeOffTaken(List<IndividualTimeOff> individualTimeOffs);

    Double calculateOverallVacationTakenFromTimeWondow(List<IndividualTimeOff> individualTimeOffs, LocalDateTime startDate, LocalDateTime endDate);

    Double calculateOverallTimeOffFromTimeWondow(List<IndividualTimeOff> individualTimeOffs, LocalDateTime startDate, LocalDateTime endDate);
}
