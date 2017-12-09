package com.recruiting.service.vacation.impl;

import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.service.employee.dto.model.EmployeeModel;
import com.recruiting.service.employee.dto.model.VacationConsumptionModel;
import com.recruiting.service.vacation.VacationCalculationService;
import com.recruiting.utils.DateTimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
//@Transactional(readOnly = true)
//@Slf4j
public class VacationCalculationServiceImpl implements VacationCalculationService {

    @Override
    public void calculateEmployeeVacationData(EmployeeModel model, List<IndividualTimeOff> timeOffs, Double vacationPerMonth, Double validVacationPeriod) {
        LocalDateTime startDate = model.getJoinDate();
        LocalDateTime endDate = model.getLeaveDate();
        if (endDate == null) endDate = LocalDateTime.now();
        Long workDurationMonths = ChronoUnit.MONTHS.between(startDate, endDate);
        Double overallVacation = calculateOverallVacationGranted(startDate, endDate, vacationPerMonth, validVacationPeriod);

        LocalDateTime indatePeriodStart = LocalDateTime.now().minusMonths(validVacationPeriod.longValue());
        LocalDateTime indatePeriodEnd = LocalDateTime.now();
        Long indateDaysCount = ChronoUnit.DAYS.between(indatePeriodStart, indatePeriodEnd);

        LocalDateTime outDatePeriodStart = model.getJoinDate();
        LocalDateTime outdatePeriodEnd = indatePeriodStart.minusDays(1);
        Long outDatedDaysCount = ChronoUnit.DAYS.between(outDatePeriodStart, outdatePeriodEnd);



        model.setVacationEarnedFromIndate(calculateOverallVacationFromIndate(startDate, endDate, vacationPerMonth, validVacationPeriod));
        model.setIndateDuration(Double.parseDouble(indateDaysCount.toString()));
        model.setVacationEarnedFromOutdated(calculateOverallVacationFromOutdated(startDate, endDate, vacationPerMonth, validVacationPeriod));
        model.setOutdateDuration(Double.parseDouble(outDatedDaysCount.toString()));
        model.setOverallVacationGranted(overallVacation);
        model.setOverallDisposableVacationTaken(calculateOverAllDisposableTimeOffTaken(timeOffs));
        model.setOverALLDisposedVacationTaken(calculateOverAllDisposedVacationTaken(timeOffs));
        model.setOverallTimeOffTaken(calculateOverAllTimeOffTaken(timeOffs));
        model.setBalancedVacationConsumption(calculateBalancedVacationConsumption(timeOffs, model.getVacationEarnedFromIndate(), model.getVacationEarnedFromOutdated(), indatePeriodStart, indatePeriodEnd, outDatePeriodStart, outdatePeriodEnd));
        model.setDisposedFromOutdatedVacationConsumption(calcualteDisposedFromOutdateVacationConsuption(
                model.getOverALLDisposedVacationTaken(),
                model.getOverallTimeOffTaken(),
                model.getVacationEarnedFromOutdate(),
                model.getVacationEarnedFromIndate()));

    }

    @Override
    public VacationConsumptionModel calculateBalancedVacationConsumption(
            List<IndividualTimeOff> timeOffs,
            Double vacationEarnedFromIndate,
            Double VacationEarnedFromOutdate,
            LocalDateTime indatePeriodStart,
            LocalDateTime indatePeriodEnd,
            LocalDateTime outDatePeriodStart,
            LocalDateTime outdatePeriodEnd) {

        VacationConsumptionModel model = new VacationConsumptionModel();
        model.setVacationTakenFromIndate(calculateOverallVacationTakenFromTimeWondow(timeOffs, indatePeriodStart, indatePeriodEnd));
        model.setVacationTakenFromOutDate(calculateOverallVacationTakenFromTimeWondow(timeOffs, outDatePeriodStart, outdatePeriodEnd));
        model.setTimeOffFromIndate(calculateOverallTimeOffFromTimeWondow(timeOffs, indatePeriodStart, indatePeriodEnd));
        model.setTimeOffFromOutdate(calculateOverallTimeOffFromTimeWondow(timeOffs, outDatePeriodStart, outdatePeriodEnd));
        setIndateVacationDebt(model, vacationEarnedFromIndate);
        setOutdateVacationDebt(model, VacationEarnedFromOutdate);
        return model;
    }

    @Override
    public VacationConsumptionModel calcualteDisposedFromOutdateVacationConsuption(
            Double overallDisposedTimeOff,
            Double overallTimeOff,
            Double vacationGrantedFromOutDate,
            Double vacationGrantedFromInDate) {
        VacationConsumptionModel model = new VacationConsumptionModel();

        if (overallDisposedTimeOff > vacationGrantedFromOutDate && overallDisposedTimeOff < (vacationGrantedFromOutDate + vacationGrantedFromInDate)) {
            Double differenceFromOutDate = overallDisposedTimeOff - vacationGrantedFromOutDate;
            Double differenceFromInDate = vacationGrantedFromInDate - differenceFromOutDate;
            model.setVacationTakenFromIndate(differenceFromOutDate);
            model.setVacationLeftFromIndate(differenceFromInDate);
            model.setVacationInAdvanceFromIndate(0D);
            model.setTimeOffFromIndate(overallTimeOff);

            model.setVacationTakenFromOutDate(vacationGrantedFromOutDate);
            model.setVacationLeftFromOutdate(0D);
            model.setVacationInAdvanceFromOutDate(0D);
            model.setTimeOffFromOutdate(0D);
            return model;

        } else if (overallDisposedTimeOff < vacationGrantedFromOutDate) {
            Double differenceFromOutDate = vacationGrantedFromOutDate - overallDisposedTimeOff;
            model.setVacationTakenFromIndate(0D);
            model.setVacationLeftFromIndate(vacationGrantedFromInDate);
            model.setVacationInAdvanceFromIndate(0D);
            model.setTimeOffFromIndate(overallTimeOff);

            model.setVacationTakenFromOutDate(overallDisposedTimeOff);
            model.setVacationLeftFromOutdate(differenceFromOutDate);
            model.setVacationInAdvanceFromOutDate(0D);
            model.setTimeOffFromOutdate(0D);
            return model;

        } else if (overallDisposedTimeOff > vacationGrantedFromOutDate && overallDisposedTimeOff > (vacationGrantedFromOutDate + vacationGrantedFromInDate)) {
            Double differenceFromOutDate = overallDisposedTimeOff - vacationGrantedFromOutDate;
            Double differenceFromInDate = differenceFromOutDate - vacationGrantedFromInDate;
            model.setVacationTakenFromIndate(vacationGrantedFromInDate);
            model.setVacationLeftFromIndate(0D);
            model.setVacationInAdvanceFromIndate(differenceFromInDate);
            model.setTimeOffFromIndate(overallTimeOff);

            model.setVacationTakenFromOutDate(vacationGrantedFromOutDate);
            model.setVacationLeftFromOutdate(0D);
            model.setVacationInAdvanceFromOutDate(0D);
            model.setTimeOffFromOutdate(0D);
            return model;

        } else if (overallDisposedTimeOff == (vacationGrantedFromOutDate + vacationGrantedFromInDate)) {
            model.setVacationTakenFromIndate(vacationGrantedFromInDate);
            model.setVacationLeftFromIndate(0D);
            model.setVacationInAdvanceFromIndate(0D);
            model.setTimeOffFromIndate(overallTimeOff);

            model.setVacationTakenFromOutDate(vacationGrantedFromOutDate);
            model.setVacationLeftFromOutdate(0D);
            model.setVacationInAdvanceFromOutDate(0D);
            model.setTimeOffFromOutdate(0D);
            return model;

        } else {
            return model;
        }
    }

    @Override
    public VacationConsumptionModel calcualteDisposedFromIndateVacationConsuption(
            Double overallDisposedTimeOff,
            Double overallTimeOff,
            Double vacationGrantedFromOutDate,
            Double vacationGrantedFromInDate) {
        return null;
    }

    @Override
    public void setIndateVacationDebt(VacationConsumptionModel model, Double vacationEarnedFromIndate) {
        Double vacationTakenFromIndate = model.getVacationTakenFromIndate();

        if (vacationTakenFromIndate > vacationEarnedFromIndate) {
            model.setVacationLeftFromIndate(0D);
            model.setVacationInAdvanceFromIndate(vacationTakenFromIndate - vacationEarnedFromIndate);
        } else if (vacationTakenFromIndate < vacationEarnedFromIndate) {
            model.setVacationLeftFromIndate(vacationEarnedFromIndate - vacationTakenFromIndate);
            model.setVacationInAdvanceFromIndate(0D);
        } else {
            model.setVacationLeftFromIndate(0D);
            model.setVacationInAdvanceFromIndate(0D);
        }
    }

    @Override
    public void setOutdateVacationDebt(VacationConsumptionModel model, Double vacationEarnedFromOutdate) {
        Double vacationTakenFromOutdate = model.getVacationTakenFromOutDate();

        if (vacationTakenFromOutdate > vacationEarnedFromOutdate) {
            model.setVacationLeftFromOutdate(0D);
            model.setVacationInAdvanceFromOutDate(vacationTakenFromOutdate - vacationEarnedFromOutdate);
        } else if (vacationTakenFromOutdate < vacationEarnedFromOutdate) {
            model.setVacationLeftFromOutdate(vacationEarnedFromOutdate - vacationTakenFromOutdate);
            model.setVacationInAdvanceFromOutDate(0D);
        } else {
            model.setVacationLeftFromOutdate(0D);
            model.setVacationInAdvanceFromOutDate(0D);
        }
    }

    @Override
    public Map<String, Long> summarizeDisposedTimeOff(List<IndividualTimeOff> timeOffs) {
        Map<String, Long> summarizedTimeOffs = new HashMap<>();
        timeOffs.stream()
                .filter(IndividualTimeOff::getDisposed)
                .filter(IndividualTimeOff::getApproved)
                .forEach(individualTimeOff -> {
                    summarizedTimeOffs.put(individualTimeOff.getReason()
                                    .getTitle(),
                            ChronoUnit.DAYS.between(individualTimeOff.getStart(), individualTimeOff.getEnd()));
                });
        return summarizedTimeOffs;
    }

    @Override
    public Double calculateOverallVacationFromOutdated(
            LocalDateTime joinDate,
            LocalDateTime endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        Long workDurationMonths = ChronoUnit.MONTHS.between(joinDate, endDate);
        if (workDurationMonths <= validVacationPeriod) return 0D;
        Double workDurationFromOutDatedMonths = workDurationMonths - validVacationPeriod;
        return workDurationFromOutDatedMonths * vacationPerMonth;
    }

    @Override
    public Double calculateOverallVacationFromIndate(
            LocalDateTime joinDate,
            LocalDateTime endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        Long workDurationMonths = ChronoUnit.MONTHS.between(joinDate, endDate);
        if (workDurationMonths > validVacationPeriod) {
            return validVacationPeriod * vacationPerMonth;
        } else {
            return workDurationMonths * vacationPerMonth;
        }
    }

    @Override
    public Double calculateOverallVacationGranted(
            LocalDateTime joinDate,
            LocalDateTime endDate,
            Double vacationPerMonth,
            Double validVacationPeriod) {
        Long workDurationMonths = ChronoUnit.MONTHS.between(joinDate, endDate);
        return workDurationMonths * vacationPerMonth;
    }


    @Override
    public Double calculateOverAllDisposableTimeOffTaken(List<IndividualTimeOff> individualTimeOffs) {
        if (individualTimeOffs == null) return null;
        final Long[] overAllDisposableVacationTaken = {0L};
        individualTimeOffs.stream()
                .filter(IndividualTimeOff::getApproved)
                .filter(individualTimeOff -> individualTimeOff.getReason().getDisposableFromVacation())
                .forEach(individualTimeOff -> overAllDisposableVacationTaken[0] += DateTimeUtils.calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd()));
        return Double.parseDouble(overAllDisposableVacationTaken[0].toString());

    }

    @Override
    public Double calculateOverAllDisposedVacationTaken(List<IndividualTimeOff> individualTimeOffs) {
        if (individualTimeOffs == null) return null;
        final Long[] overAllDisposableVacationTaken = {0L};
        individualTimeOffs.stream()
                .filter(IndividualTimeOff::getApproved)
                .filter(IndividualTimeOff::getDisposed)
                .filter(individualTimeOff -> individualTimeOff.getReason().getDisposableFromVacation())
                .forEach(individualTimeOff -> overAllDisposableVacationTaken[0] += DateTimeUtils.calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd()));
        return Double.parseDouble(overAllDisposableVacationTaken[0].toString());

    }

    @Override
    public Double calculateOverAllTimeOffTaken(List<IndividualTimeOff> individualTimeOffs) {
        if (individualTimeOffs == null) return null;
        final Long[] overAllTimeOffTaken = {0L};
        individualTimeOffs.stream()
                .filter(IndividualTimeOff::getApproved)
                .filter(individualTimeOff -> individualTimeOff.getReason().getDisposableFromVacation())
                .filter(individualTimeOff -> !individualTimeOff.getDisposed())
                .forEach(individualTimeOff -> overAllTimeOffTaken[0] += DateTimeUtils.calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd()));
        return Double.parseDouble(overAllTimeOffTaken[0].toString());

    }

    @Override
    public Double calculateOverallVacationTakenFromTimeWondow(List<IndividualTimeOff> individualTimeOffs, LocalDateTime startDate, LocalDateTime endDate) {
        if (individualTimeOffs == null) return null;
        final Long[] overAllVacationTaken = {0L};
        individualTimeOffs.stream()
                .filter(IndividualTimeOff::getApproved)
                .filter(individualTimeOff -> individualTimeOff.getReason().getDisposableFromVacation())
                .filter(IndividualTimeOff::getDisposed)
                .filter(individualTimeOff -> DateTimeUtils.dateIsBetweenIncludingEndPoints(startDate, endDate, individualTimeOff.getStart(), individualTimeOff.getEnd()))
                .forEach(individualTimeOff -> overAllVacationTaken[0] += DateTimeUtils.calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd()));
        return Double.parseDouble(overAllVacationTaken[0].toString());
    }

    @Override
    public Double calculateOverallTimeOffFromTimeWondow(List<IndividualTimeOff> individualTimeOffs, LocalDateTime startDate, LocalDateTime endDate) {
        if (individualTimeOffs == null) return null;
        final Long[] overAllTimeOffTaken = {0L};
        individualTimeOffs.stream()
                .filter(IndividualTimeOff::getApproved)
                .filter(individualTimeOff -> individualTimeOff.getReason().getDisposableFromVacation())
                .filter(individualTimeOff -> !individualTimeOff.getDisposed())
                .filter(individualTimeOff -> DateTimeUtils.dateIsBetweenIncludingEndPoints(startDate, endDate, individualTimeOff.getStart(), individualTimeOff.getEnd()))
                .forEach(individualTimeOff -> overAllTimeOffTaken[0] += DateTimeUtils.calculateDays(individualTimeOff.getStart(), individualTimeOff.getEnd()));
        return Double.parseDouble(overAllTimeOffTaken[0].toString());
    }

}
