package com.p_kor.insurance.dto;

import java.time.LocalDate;

public record TravelCalculatePremiumRequest(
        String personFirstName,
        String personLastName,
        LocalDate agreementDateFrom,
        LocalDate agreementDateTo) {
}