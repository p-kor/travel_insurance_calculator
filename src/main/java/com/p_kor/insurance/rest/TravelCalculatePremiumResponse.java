package com.p_kor.insurance.rest;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TravelCalculatePremiumResponse(String personFirstName,
                                             String personLastName,
                                             LocalDate agreementDateFrom,
                                             LocalDate agreementDateTo,
                                             BigDecimal agreementPrice) {}

