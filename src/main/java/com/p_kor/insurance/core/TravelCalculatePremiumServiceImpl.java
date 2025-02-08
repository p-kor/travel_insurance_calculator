package com.p_kor.insurance.core;

import com.p_kor.insurance.rest.TravelCalculatePremiumRequest;
import com.p_kor.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TravelCalculatePremiumServiceImpl
        implements TravelCalculatePremiumService {

    private final DateTimeService dateTimeService;
    private final AgreementPriceService agreementPriceService;

    TravelCalculatePremiumServiceImpl(DateTimeService dateTimeService,
                                      AgreementPriceService agreementPriceService) {
        this.dateTimeService = dateTimeService;
        this.agreementPriceService = agreementPriceService;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        String firstName = request.personFirstName();
        String lastName = request.personLastName();
        LocalDate agreementDateFrom = request.agreementDateFrom();
        LocalDate agreementDateTo = request.agreementDateTo();
        long days = dateTimeService.daysBetweenDates(agreementDateFrom, agreementDateTo);
        BigDecimal agreementPrice = agreementPriceService.calculateAgreementPrice(days);

        return new TravelCalculatePremiumResponse(firstName, lastName, agreementDateFrom, agreementDateTo, agreementPrice);
    }

}
