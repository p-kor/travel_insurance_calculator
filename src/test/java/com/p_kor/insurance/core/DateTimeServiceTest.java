package com.p_kor.insurance.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeServiceTest {

    @ParameterizedTest(name = "test with {2}: between {0} and {1} should be {3} days")
    @MethodSource("com.p_kor.insurance.testdata.TestDataDateTime#dateFactory")
    void testDaysPeriodBetweenIncrementingDates(
            LocalDate dateFrom,
            LocalDate dateTo,
            String testName,
            long expectedDays) {

        DateTimeService dateTimeService = new DateTimeService();
        long actualDays = dateTimeService.daysBetweenDates(dateFrom, dateTo);
        assertEquals(expectedDays, actualDays, "Wrong days between dates");
    }
}