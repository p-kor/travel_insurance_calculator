package com.p_kor.insurance.rest;

import java.time.LocalDate;

public record TravelCalculatePremiumRequest(String personFirstName,
                                            String personLastName,
                                            LocalDate agreementDateFrom,
                                            LocalDate agreementDateTo) {
}