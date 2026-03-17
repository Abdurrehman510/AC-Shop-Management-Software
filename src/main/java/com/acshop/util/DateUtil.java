package com.acshop.util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date calculateNextServiceDate(Date dop, Date currentNextServiceDate, int remainingServices) {
        if (remainingServices >= 0) {
            if (currentNextServiceDate == null) {
                return addMonths(dop, 4); // First service date based on date of purchase
            } else {
                // Subsequent services every 4 months from the previous service date
                return addMonths(currentNextServiceDate, 4);
            }
        }
        return null; // No more services remaining
    }

    public static Date addMonths(Date date, int months) {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    public static String getTimePeriodBetweenDates(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) return "Unknown";
        
        endDate = endDate.plusYears(1);
        if (startDate.isAfter(endDate)) {
            LocalDate temp = startDate;
            startDate = endDate;
            endDate = temp;
        }

        Period period = Period.between(startDate, endDate);

        int months = period.getMonths();
        int days = period.getDays();

        if (period.getYears() == 1) {
            return period.getYears() + " Year";
        } else {
            return days + " Days and " + months + " Months";
        }
    }
}
