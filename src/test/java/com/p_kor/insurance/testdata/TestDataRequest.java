package com.p_kor.insurance.testdata;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.stream.Stream;

public class TestDataRequest {

    public static final long DAYS = 43L;

    public static final TravelCalculatePremiumRequest VALID_REQUEST =
            TravelCalculatePremiumRequest.builder()
                    .personFirstName("Ivan")
                    .personLastName("Ivanov")
                    .agreementDateFrom(LocalDate.now())
                    .agreementDateTo(LocalDate.now().plusDays(DAYS))
                    .build();

    private static Stream<Arguments> validRequestFactory() {
        return Stream.of(Arguments.arguments(TestDataRequest.VALID_REQUEST, "valid request"));
    }

    private static Stream<Arguments> emptyRequestFactory() {
        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .build();
        return Stream.of(Arguments.arguments(request, "request with all fields null"));
    }

    private static Stream<Arguments> invalidRequestFactory() {
        Stream.Builder<Arguments> streamBuilder = Stream.builder();

        TravelCalculatePremiumRequest request1 = TravelCalculatePremiumRequest.builder()
                .personLastName("Ivanov")
                .agreementDateFrom(LocalDate.now())
                .agreementDateTo(LocalDate.now().plusDays(DAYS))
                .build();

        streamBuilder.add(Arguments.arguments(request1, "firstName is null"));

        TravelCalculatePremiumRequest request2 = TravelCalculatePremiumRequest.builder()
                .personFirstName("Ivan")
                .agreementDateFrom(LocalDate.now())
                .agreementDateTo(LocalDate.now().plusDays(DAYS))
                .build();

        streamBuilder.add(Arguments.arguments(request2, "lastName is null"));

        TravelCalculatePremiumRequest request3 = TravelCalculatePremiumRequest.builder()
                .personFirstName("Ivan")
                .agreementDateFrom(LocalDate.now())
                .agreementDateTo(LocalDate.now().plusDays(DAYS))
                .build();

        streamBuilder.add(Arguments.arguments(request3, "lastName is empty string"));

        TravelCalculatePremiumRequest request4 = TravelCalculatePremiumRequest.builder()
                .personFirstName("Ivan")
                .personLastName("Ivanov")
                .agreementDateTo(LocalDate.now().plusDays(DAYS))
                .build();

        streamBuilder.add(Arguments.arguments(request4, "agreementDateFrom is null"));

        TravelCalculatePremiumRequest request5 = TravelCalculatePremiumRequest.builder()
                .personFirstName("Ivan")
                .personLastName("Ivanov")
                .agreementDateFrom(LocalDate.now().minusDays(1))
                .agreementDateTo(LocalDate.now().plusDays(DAYS))
                .build();

        streamBuilder.add(Arguments.arguments(request5, "agreementDateFrom is before the current date"));

        TravelCalculatePremiumRequest request6 = TravelCalculatePremiumRequest.builder()
                .personFirstName("Ivan")
                .personLastName("Ivanov")
                .agreementDateFrom(LocalDate.now())
                .build();

        streamBuilder.add(Arguments.arguments(request6, "agreementDateTo is null"));

        TravelCalculatePremiumRequest request7 = TravelCalculatePremiumRequest.builder()
                .personFirstName("Ivan")
                .personLastName("Ivanov")
                .agreementDateFrom(LocalDate.now())
                .agreementDateTo(LocalDate.now().minusDays(1))
                .build();

        streamBuilder.add(Arguments.arguments(request7, "agreementDateTo is before the current date"));

        return streamBuilder.build();
    }

}
