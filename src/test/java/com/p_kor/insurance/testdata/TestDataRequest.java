package com.p_kor.insurance.testdata;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.params.provider.Arguments;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

public class TestDataRequest {

    public static final long DAYS = 43L;
    public static String PERSON_FIRSTNAME = "Ivan";
    public static String PERSON_LASTNAME = "Ivanov";
    public static LocalDate AGREEMENT_DATEFROM = LocalDate.now().plusDays(1);
    public static LocalDate AGREEMENT_DATETO = AGREEMENT_DATEFROM.plusDays(DAYS);
    public static BigDecimal AGREEMENT_PRICE = new BigDecimal(DAYS);

    public static final TravelCalculatePremiumRequest VALID_REQUEST =
            TravelCalculatePremiumRequest.builder()
                    .personFirstName(PERSON_FIRSTNAME)
                    .personLastName(PERSON_LASTNAME)
                    .agreementDateFrom(AGREEMENT_DATEFROM)
                    .agreementDateTo(AGREEMENT_DATETO)
                    .build();

    public static final TravelCalculatePremiumResponse VALID_RESPONSE =
            TravelCalculatePremiumResponse.builder()
                    .personFirstName(PERSON_FIRSTNAME)
                    .personLastName(PERSON_LASTNAME)
                    .agreementDateFrom(AGREEMENT_DATEFROM)
                    .agreementDateTo(AGREEMENT_DATETO)
                    .agreementPrice(AGREEMENT_PRICE)
                    .build();

    public static final TravelCalculatePremiumRequest EMPTY_REQUEST =
            TravelCalculatePremiumRequest.builder()
                    .build();

    public static Stream<Arguments> validRequestFactory() {

        String testName = "valid request";
        TravelCalculatePremiumRequest request = VALID_REQUEST;

        return Stream.of(Arguments.argumentSet(testName, request));
    }

    public static Stream<Arguments> emptyRequestFactory() {

        String testName = "request with all fields null";
        TravelCalculatePremiumRequest request = EMPTY_REQUEST;

        return Stream.of(Arguments.argumentSet(testName, request));
    }

    public static Stream<Arguments> invalidRequestFactory() {
        Stream.Builder<Arguments> streamBuilder = Stream.builder();

        String testName = "firstName is null";
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "firstName is blank";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(" ")
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "lastName is null";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "lastName is blank";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(" ")
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "agreementDateFrom is null";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "agreementDateFrom is before the current date";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(LocalDate.now().minusDays(1))
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "agreementDateTo is null";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "agreementDateTo is before the agreementDateFrom";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATETO)
                .agreementDateTo(AGREEMENT_DATEFROM)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        testName = "agreementDateTo is the same as the agreementDateFrom";
        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATEFROM)
                .build();

        streamBuilder.add(Arguments.argumentSet(testName, request));

        return streamBuilder.build();
    }

}
