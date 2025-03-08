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
    public static LocalDate AGREEMENT_DATEFROM = LocalDate.now();
    public static LocalDate AGREEMENT_DATETO = LocalDate.now().plusDays(DAYS);
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
        return Stream.of(Arguments.arguments(VALID_REQUEST, "valid request"));
    }

    public static Stream<Arguments> emptyRequestFactory() {
        return Stream.of(Arguments.arguments(EMPTY_REQUEST, "request with all fields null"));
    }

    public static Stream<Arguments> invalidRequestFactory() {
        Stream.Builder<Arguments> streamBuilder = Stream.builder();

        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.arguments(request, "firstName is null"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(" ")
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.arguments(request, "firstName is blank"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.arguments(request, "lastName is null"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(" ")
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.arguments(request, "lastName is blank string"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.arguments(request, "agreementDateFrom is null"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(LocalDate.now().minusDays(1))
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.arguments(request, "agreementDateFrom is before the current date"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .build();

        streamBuilder.add(Arguments.arguments(request, "agreementDateTo is null"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATETO)
                .build();

        streamBuilder.add(Arguments.arguments(request, "agreementDateTo is before the agreementDateFrom"));

        request = TravelCalculatePremiumRequest.builder()
                .personFirstName(PERSON_FIRSTNAME)
                .personLastName(PERSON_LASTNAME)
                .agreementDateFrom(AGREEMENT_DATEFROM)
                .agreementDateTo(AGREEMENT_DATEFROM)
                .build();

        streamBuilder.add(Arguments.arguments(request, "agreementDateTo is the same as the agreementDateTo "));

        return streamBuilder.build();
    }

}
