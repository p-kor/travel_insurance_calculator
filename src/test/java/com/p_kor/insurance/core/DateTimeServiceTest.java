package com.p_kor.insurance.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DateTimeServiceTest {

    private DateTimeService dateTimeService;
    private static final int DAYS = 5;
    private static LocalDate date1;
    private static LocalDate date2;

    @BeforeAll
    static void setUp() {
        date1 = LocalDate.now();
        date2 = date1.plusDays(DAYS);
    }

    @BeforeEach
    void init() {
        dateTimeService = new DateTimeService();
    }

    @Test
    void testDaysPeriodBetweenIncrementingDates() {
        long expectedDays = DAYS;
        long actualDays = dateTimeService.daysBetweenDates(date1, date2);
        assertEquals(expectedDays, actualDays, "Wrong days between dates");
    }

    @Test
    void testThatZeroDaysBetweenTheSameDate() {
        long expectedDays = 0L;
        long actualDays = dateTimeService.daysBetweenDates(date1, date1);
        assertEquals(expectedDays, actualDays, "Should be zero days between the same date");
    }

    @Test
    void testDaysPeriodBetweenDecrementingDatesIsNegative() {
        long actualDays = dateTimeService.daysBetweenDates(date2, date1);
        assertTrue(actualDays < 0, "Days between decrementing dates should be negative");
    }

    @Test
    void testDaysPeriodBetweenDecrementingDates() {
        long expectedDays = -DAYS;
        long actualDays = dateTimeService.daysBetweenDates(date2, date1);
        assertEquals(expectedDays, actualDays, "Wrong days between dates");
    }

}