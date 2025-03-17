package com.p_kor.insurance.core;

import com.p_kor.insurance.core.validation.TravelCalculatePremiumRequestValidationService;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.dto.ValidationError;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumUnderwritingService underwritingService;
    private final TravelCalculatePremiumRequestValidationService requestValidationService;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {

        List<ValidationError> validationErrors = requestValidationService.validate(request);

        if (!validationErrors.isEmpty()) {
            return TravelCalculatePremiumResponse.builder()
                    .validationErrors(validationErrors)
                    .build();
        }

        String firstName = request.personFirstName();
        String lastName = request.personLastName();
        LocalDate agreementDateFrom = request.agreementDateFrom();
        LocalDate agreementDateTo = request.agreementDateTo();
        BigDecimal agreementPrice = underwritingService.calculateAgreementPrice(request);

        return TravelCalculatePremiumResponse.builder()
                .personFirstName(firstName)
                .personLastName(lastName)
                .agreementDateFrom(agreementDateFrom)
                .agreementDateTo(agreementDateTo)
                .agreementPrice(agreementPrice)
                .build();
    }

}
