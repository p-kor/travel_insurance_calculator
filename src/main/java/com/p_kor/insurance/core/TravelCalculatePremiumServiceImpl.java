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

        List<ValidationError> validationErrors = requestValidator.validate(request);
        if (!validationErrors.isEmpty()) {
            return TravelCalculatePremiumResponse.builder().validationErrors(validationErrors).build();
        }

        String firstName = request.personFirstName();
        String lastName = request.personLastName();
        LocalDate agreementDateFrom = request.agreementDateFrom();
        LocalDate agreementDateTo = request.agreementDateTo();
        long days = dateTimeService.daysBetweenDates(agreementDateFrom, agreementDateTo);
        BigDecimal agreementPrice = agreementPriceService.calculateAgreementPrice(days);

        return TravelCalculatePremiumResponse.builder()
                .personFirstName(firstName)
                .personLastName(lastName)
                .agreementDateFrom(agreementDateFrom)
                .agreementDateTo(agreementDateTo)
                .agreementPrice(agreementPrice)
                .build();
    }

}
