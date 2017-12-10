package com.recruiting.csv;

import com.recruiting.domain.Employee;
import com.recruiting.domain.IndividualTimeOff;
import com.recruiting.domain.TimeOffType;
import com.recruiting.repository.EmployeeRepository;
import com.recruiting.repository.IndividualTimeOffRepository;
import com.recruiting.repository.TimeOffTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class EmployeeCSVParser {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TimeOffTypeRepository timeOffTypeRepository;

    @Autowired
    IndividualTimeOffRepository individualTimeOffRepository;

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';

    public void generateDataFromCSV(final String name) {
        final String cvsSplitBy = ",";
        final ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        final InputStream is = classloader.getResourceAsStream(name);
        Scanner scanner = new Scanner(is);
        List<String> line = parseLine(scanner.nextLine());
        String username = line.get(7);
        while (scanner.hasNext()) {
            List<String> data = parseLine(scanner.nextLine());
            convertDomainIndividualTimeOffAndPersist(username, data);
        }
    }


    private void convertDomainIndividualTimeOffAndPersist(String username, final List<String> data) {
        final Employee employee = employeeRepository.findByUsername(username);
        final TimeOffType timeOffType =  timeOffTypeRepository.findByTitle(data.get(1));
        IndividualTimeOff individualTimeOff = new IndividualTimeOff();
        individualTimeOff.setReason(timeOffType);

        individualTimeOff.setStart(LocalDateTime.of(LocalDate.parse(data.get(2), formatter), LocalTime.of(10,0)));
        individualTimeOff.setEnd(LocalDateTime.of(LocalDate.parse(data.get(3), formatter), LocalTime.of(19,0)));
        individualTimeOff.setDisposed(Boolean.valueOf(data.get(4)));
        individualTimeOff.setDisposed_hr(individualTimeOff.getDisposed());
        individualTimeOff.setUser(employee);
        individualTimeOff.setApproved(true);

        individualTimeOffRepository.save(individualTimeOff);
    }

    private static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public static List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
    }

    private static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        if (customQuote == ' ') {
            customQuote = DEFAULT_QUOTE;
        }

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }


}
