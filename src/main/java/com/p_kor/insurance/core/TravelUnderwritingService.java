package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
class TravelUnderwritingService {

    private final DateTimeService dateTimeService;

    @Autowired
    TravelUnderwritingService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.agreementDateFrom();
        LocalDate dateTo = request.agreementDateTo();
        long days = dateTimeService.daysBetweenDates(dateFrom, dateTo);
        return new BigDecimal(days);
    }
}
