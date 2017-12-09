package com.recruiting.utils;

import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;

/**
 * Created by Martha on 6/9/2017.
 */
public class DateTimeUtils {

    public static Long calculateDays(LocalDateTime start, LocalDateTime end) {
        return getWorkingDaysBetweenTwoDates(Date.from(start.atZone(ZoneId.systemDefault()).toInstant()), Date.from(end.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public static boolean dateIsBetweenIncludingEndPoints(
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final LocalDateTime date) {
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    public static boolean dateIsBetweenIncludingEndPoints(
            final LocalDateTime startDate,
            final LocalDateTime endDate,
            final LocalDateTime comparableStartDate,
            final LocalDateTime comparableEndDate) {
        return comparableStartDate.compareTo(startDate) >= 0 && comparableStartDate.compareTo(endDate) <= 0 && comparableEndDate.compareTo(startDate) >= 0 && comparableEndDate.compareTo(endDate) <= 0;
    }

    public static Long sum(Collection<Long> days) {
        Long sum = 0L;
        for (Long value : days) {
            sum += value;
        }
        return sum;
    }

    public static Long getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        Long workDays = 0L;

        //Return 0 if start and end are the same
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0L;
        }

        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }

        do {
            //excluding start date
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

        return workDays;
    }

}
