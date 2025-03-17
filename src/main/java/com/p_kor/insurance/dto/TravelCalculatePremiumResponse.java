package com.p_kor.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@JsonPropertyOrder({"validationErrors"})
//@JsonInclude(JsonInclude.Include.NON_NULL)
public record TravelCalculatePremiumResponse(
        String personFirstName,
        String personLastName,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate agreementDateFrom,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate agreementDateTo,
        BigDecimal agreementPrice,
        List<ValidationError> validationErrors) {
}
