package com.p_kor.insurance.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TravelCalculatePremiumRequest(
        String personFirstName,
        String personLastName,
        LocalDate agreementDateFrom,
        LocalDate agreementDateTo) {
}