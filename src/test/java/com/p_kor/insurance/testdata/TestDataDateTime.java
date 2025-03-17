package com.p_kor.insurance.testdata;

import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.stream.Stream;

public class TestDataDateTime {

    public static final long DAYS = 5L;

    public static Stream<Arguments> dateFactory() {

        Stream.Builder<Arguments> streamBuilder = Stream.builder();

        String testName = "second date is after the first date";
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = date1.plusDays(DAYS);
        long expectedResult = DAYS;

        streamBuilder.add(Arguments.argumentSet(testName, date1, date2, expectedResult));

        testName = "second date is equal to the first date";
        date1 = LocalDate.now();
        date2 = date1;
        expectedResult = 0L;

        streamBuilder.add(Arguments.argumentSet(testName, date1, date2, expectedResult));

        testName = "second date is before the first date";
        date1 = LocalDate.now();
        date2 = date1.minusDays(DAYS);
        expectedResult = -DAYS;

        streamBuilder.add(Arguments.argumentSet(testName, date1, date2, expectedResult));

        return streamBuilder.build();
    }
}
