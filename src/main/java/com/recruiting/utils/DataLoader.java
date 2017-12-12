package com.recruiting.utils;

import com.recruiting.csv.EmployeeCSVParser;
import com.recruiting.domain.*;
import com.recruiting.domain.types.VacationDisposeType;
import com.recruiting.repository.*;
import org.assertj.core.util.Lists;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author Marta Ginosyan
 */

public class DataLoader {

    public static void createCompanyData(
            CompanyConfigRepository companyConfigRepository,
            TimeOffTypeRepository timeOffTypeRepository,
            WorkingHoursSchemeRepository workingHoursSchemeRepository) {

        TimeOffType slackTimeOffType1 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Vacation", true, true, true));
        TimeOffType slackTimeOffType2 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Day Off", false, true, true));
        TimeOffType slackTimeOffType3 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Medical Leave", false, true, true));
        TimeOffType slackTimeOffType4 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Missed Key", true, false, true));
        TimeOffType slackTimeOffType5 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Maternity Leave", false, false, true));
        TimeOffType slackTimeOffType6 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Business Trip", true, false, true));
        TimeOffType slackTimeOffType7 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Unpaid Day Off", false, false, true));
        TimeOffType slackTimeOffType8 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Working From Home", true, false, true));
        TimeOffType slackTimeOffType9 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Holiday Time Off", true, false, false));
        TimeOffType slackTimeOffType10 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Sick Leave", false, true, false));
        TimeOffType slackTimeOffType11 = timeOffTypeRepository.save(new TimeOffType(BusKeyGen.nextKey(), "Weekend Paid Work", true, false, false));

        WorkingHoursScheme workingHoursScheme1 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed In-Out", new Short("8"), LocalTime.of(10, 0), LocalTime.of(19, 0), true));
        WorkingHoursScheme workingHoursScheme2 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed Daily Hours", new Short("8"), null, null, false));
        WorkingHoursScheme workingHoursScheme3 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed Weekly Hours", new Short("40"), null, null, false));
        WorkingHoursScheme workingHoursScheme4 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Fixed Monthly Hours", new Short("173"), null, null, false));
        WorkingHoursScheme workingHoursScheme5 = workingHoursSchemeRepository.save(new WorkingHoursScheme(BusKeyGen.nextKey(), "Free Hours", null, null, null, false));

        CompanyConfig companyConfig = companyConfigRepository.save(new CompanyConfig(
                BusKeyGen.nextKey(),
                "SFL",
                "info@sflpro.com",
                VacationDisposeType.FROM_INDATE_VACATION.name(),
                VacationDisposeType.FROM_OUTDATED_VACATION.name(),
                workingHoursScheme1,
                1.67,
                18D,
                LocalTime.of(13, 0),
                1D,
                true,
                false,
                true
        ));
    }

    public static void createUserData(UserRepository userRepository, AuthorityRepository authorityRepository, WorkingHoursSchemeRepository workingHoursSchemeRepository) {

        WorkingHoursScheme workingHoursScheme = workingHoursSchemeRepository.findByTitle("Fixed In-Out");
        Authority authority = authorityRepository.save(new Authority(BusKeyGen.nextKey(), "EMPLOYEE"));
        Authority authority_admin = authorityRepository.save(new Authority(BusKeyGen.nextKey(), "ADMIN"));
        Employee alfred = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "alfred.kaghyan@sflpro.com",
                "123456",
                "Alfred Kaghyan",
                null,
                true,
                true,
                true,
                true,
                true,
                LocalDateTime.of(2014, 10, 1, 10, 00),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme,
                false));

        Employee hayk = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "hayk.mkrtchyan@sflpro.com",
                "123456",
                "Hayk Mkrtchyan",
                null,
                true,
                true,
                true,
                true,
                true,
                LocalDateTime.of(2012, 11, 19, 10, 0),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme,
                false));

        Employee ani = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "ani.aghababyan@sflpro.com",
                "123456",
                "Ani Aghababyan",
                null,
                true,
                true,
                true,
                true,
                true,
                LocalDateTime.of(2010, 1, 12, 10, 0),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme,
                false));

        Employee tatevik = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "tatevik.mailyan@sflpro.com",
                "123456",
                "Tatevik Mailyan",
                null,
                true,
                true,
                true,
                true,
                true,
                LocalDateTime.of(2015, 6, 1, 10, 0),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme,
                false));

        Employee varduhi = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "varduhi.petrosyan@sflpro.com",
                "123456",
                "Varduhi Petrosyan",
                null,
                true,
                true,
                true,
                true,
                true,
                LocalDateTime.of(2011, 12, 27, 10, 00),
                null,
                Lists.newArrayList(authority, authority_admin),
                workingHoursScheme,
                false));

        Employee lilit = userRepository.save(new Employee(
                BusKeyGen.nextKey(),
                "lilit.mkrtchyan@sflpro.com",
                "123456",
                "Lilit Mkrtchyan",
                null,
                true,
                true,
                true,
                true,
                true,
                LocalDateTime.of(2014, 6, 23, 10, 0),
                null,
                Lists.newArrayList(authority),
                workingHoursScheme,
                false));

    }

    public static void createNonApprovedIndividualTimeOffs(EmployeeRepository employeeRepository, IndividualTimeOffRepository individualTimeOffRepository, TimeOffTypeRepository timeOffTypeRepository) {
        List<Employee> employees = employeeRepository.findAll();
        Random random = new Random();
        List<TimeOffType> timeOffTypes = timeOffTypeRepository.findAllByIsValidTrue();
        employees.forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                Integer start = random.nextInt(6) + 2;
                Integer end = random.nextInt(14) + 6;
                Integer timeOffRandom = random.nextInt(timeOffTypes.size());
                IndividualTimeOff individualTimeOff = new IndividualTimeOff();
                individualTimeOff.setStart(LocalDateTime.of(LocalDate.now(), LocalTime.MIN)
                        .plusDays(Long.parseLong(start.toString()))
                        .plusHours(10));
                individualTimeOff.setEnd(LocalDateTime.of(LocalDate.now(), LocalTime.MAX)
                        .plusDays(Long.parseLong(end.toString()))
                        .plusHours(19));
                individualTimeOff.setUser(employee);
                individualTimeOff.setDisposed(false);
                individualTimeOff.setDisposed_hr(false);
                individualTimeOff.setApproved(false);
                individualTimeOff.setDisposed_hr(false);
                individualTimeOff.setReason(timeOffTypeRepository.findOne(timeOffTypes.get(timeOffRandom)
                        .getId()));
                individualTimeOffRepository.save(individualTimeOff);
            }
        });
    }

    public static void loadIndividualTimeOffs(EmployeeCSVParser employeeCSVParser) {
        employeeCSVParser.generateDataFromCSV("csv/alfred.csv");
        employeeCSVParser.generateDataFromCSV("csv/ani.csv");
        employeeCSVParser.generateDataFromCSV("csv/hayk.csv");
        employeeCSVParser.generateDataFromCSV("csv/lilit.csv");
        employeeCSVParser.generateDataFromCSV("csv/tatev.csv");
        employeeCSVParser.generateDataFromCSV("csv/varduhi.csv");
    }

}
