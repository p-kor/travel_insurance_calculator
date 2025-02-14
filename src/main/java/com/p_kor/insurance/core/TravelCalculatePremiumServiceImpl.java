package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.dto.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TravelCalculatePremiumServiceImpl
        implements TravelCalculatePremiumService {

    private final DateTimeService dateTimeService;
    private final AgreementPriceService agreementPriceService;
    private final TravelCalculatePremiumRequestValidator requestValidator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> requestValidationErrors = requestValidator.validate(request);
        if (!requestValidationErrors.isEmpty()) {
            return new TravelCalculatePremiumResponse(requestValidationErrors);
        }

        String firstName = request.getPersonFirstName();
        String lastName = request.getPersonLastName();
        LocalDate agreementDateFrom = request.getAgreementDateFrom();
        LocalDate agreementDateTo = request.getAgreementDateTo();
        long days = dateTimeService.daysBetweenDates(agreementDateFrom, agreementDateTo);
        BigDecimal agreementPrice = agreementPriceService.calculateAgreementPrice(days);

        return new TravelCalculatePremiumResponse(firstName, lastName, agreementDateFrom, agreementDateTo, agreementPrice);
    }

}
