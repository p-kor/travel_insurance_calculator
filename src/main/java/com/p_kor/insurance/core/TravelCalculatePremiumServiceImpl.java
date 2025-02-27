package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component()
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelUnderwritingService travelUnderwritingService;
    private final TravelCalculatePremiumRequestValidator requestValidator;

    @Autowired
    public TravelCalculatePremiumServiceImpl(
            TravelUnderwritingService travelUnderwritingService,
            TravelCalculatePremiumRequestValidator requestValidator) {

        this.travelUnderwritingService = travelUnderwritingService;
        this.requestValidator = requestValidator;
    }


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> validationErrors = requestValidator.validate(request);

        if (!validationErrors.isEmpty()) {
            return TravelCalculatePremiumResponse.builder()
                    .validationErrors(validationErrors)
                    .build();
        }

        String firstName = request.personFirstName();
        String lastName = request.personLastName();
        LocalDate agreementDateFrom = request.agreementDateFrom();
        LocalDate agreementDateTo = request.agreementDateTo();
        BigDecimal agreementPrice = travelUnderwritingService.calculateAgreementPrice(request);

        return TravelCalculatePremiumResponse.builder()
                .personFirstName(firstName)
                .personLastName(lastName)
                .agreementDateFrom(agreementDateFrom)
                .agreementDateTo(agreementDateTo)
                .agreementPrice(agreementPrice)
                .build();
    }

}
