package com.p_kor.insurance.testdata;

import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.stream.Stream;

public class TestDataDateTime {

    public static final long DAYS = 5L;

    public static Stream<Arguments> dateFactory() {

        Stream.Builder<Arguments> streamBuilder = Stream.builder();

        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.plusDays(DAYS);
        String testName = "second date is after the first date";
        long expectedResult = DAYS;

        streamBuilder.add(Arguments.arguments(date1, date2, testName, expectedResult));

        date1 = LocalDate.now();
        date2 = date1;
        testName = "both dates are equal";
        expectedResult = 0L;

        streamBuilder.add(Arguments.arguments(date1, date2, testName, expectedResult));

        date1 = LocalDate.now();
        date2 = date1.minusDays(DAYS);
        testName = "second date is before the first date";
        expectedResult = -DAYS;

        streamBuilder.add(Arguments.arguments(date1, date2, testName, expectedResult));

        return streamBuilder.build();
    }
}
