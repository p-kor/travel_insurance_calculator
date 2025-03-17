package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumUnderwritingService {

    private final DateTimeService dateTimeService;

    BigDecimal calculateAgreementPrice(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.agreementDateFrom();
        LocalDate dateTo = request.agreementDateTo();
        long days = dateTimeService.daysBetweenDates(dateFrom, dateTo);
        return new BigDecimal(days);
    }
}
