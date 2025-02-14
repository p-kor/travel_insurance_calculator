package com.p_kor.insurance.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record TravelCalculatePremiumResponse(
        String personFirstName,
        String personLastName,
        LocalDate agreementDateFrom,
        LocalDate agreementDateTo,
        BigDecimal agreementPrice,
        List<ValidationError> validationErrors) {
}
