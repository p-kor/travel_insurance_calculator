package com.p_kor.insurance.testdata;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;

import java.time.LocalDate;

public class TestData {

    public static final long DAYS = 43L;

    public static final TravelCalculatePremiumRequest VALID_REQUEST =
            TravelCalculatePremiumRequest.builder()
                    .personFirstName("Ivan")
                    .personLastName("Ivanov")
                    .agreementDateFrom(LocalDate.now())
                    .agreementDateTo(LocalDate.now().plusDays(DAYS))
                    .build();

    public static final TravelCalculatePremiumRequest REQUEST_INVALID_FIRSTNAME =
            TravelCalculatePremiumRequest.builder()
                    .personLastName("Ivanov")
                    .agreementDateFrom(LocalDate.now())
                    .agreementDateTo(LocalDate.now().plusDays(DAYS))
                    .build();
}
