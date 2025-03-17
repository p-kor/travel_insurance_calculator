package com.p_kor.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeServiceTest {

    private TestInfo testInfo;

    @BeforeEach
    void setUp(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @ParameterizedTest(name = "{argumentSetName}")
    @MethodSource("com.p_kor.insurance.testdata.TestDataDateTime#dateFactory")
    @Tag("UnitTest")
    @DisplayName("Test days period between dates")
    void testDaysPeriodBetweenIncrementingDates(LocalDate dateFrom, LocalDate dateTo, long expectedDays) {

        DateTimeService dateTimeService = new DateTimeService();
        long actualDays = dateTimeService.daysBetweenDates(dateFrom, dateTo);
        String errorMessage = "{%s}: between %s and %s should be %d days"
                .formatted(testInfo.getDisplayName(), dateFrom, dateTo, expectedDays);

        assertEquals(expectedDays, actualDays, errorMessage);
    }
}