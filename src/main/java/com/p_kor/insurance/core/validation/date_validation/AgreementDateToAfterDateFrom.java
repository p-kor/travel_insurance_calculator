package com.p_kor.insurance.core.validation.date_validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
class AgreementDateToAfterDateFrom implements TravelCalculatePremiumRequestValidator {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {

        String field = "agreementDateTo";
        String message = "Date TO must be after Date FROM";
        LocalDate dateFrom = request.agreementDateFrom();
        LocalDate dateTo = request.agreementDateTo();
        boolean isValidationError =
                (dateFrom != null)
                && (dateTo != null)
                && (dateFrom.isAfter(LocalDate.now()))
                && (!dateTo.isAfter(dateFrom));

        return isValidationError
                ? Optional.of(new ValidationError(field, message))
                : Optional.empty();
    }
}
