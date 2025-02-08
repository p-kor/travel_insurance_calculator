package com.p_kor.insurance.core;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
class DateTimeService {

    /**
     * Calculates the number of days between two dates.
     *
     * @param dateFrom the start date
     * @param dateTo   the end date
     * @return the number of days between dateFrom and dateTo
     */
    long daysBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        return dateFrom.until(dateTo, ChronoUnit.DAYS);
    }
}
