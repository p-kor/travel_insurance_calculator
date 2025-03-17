package com.p_kor.insurance.core.validation.date_validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
class AgreementDateFromAfterCurrent implements TravelCalculatePremiumRequestValidator {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {

        String field = "agreementDateFrom";
        String message = "Date FROM must be after current date";
        LocalDate dateFrom = request.agreementDateFrom();
        boolean isValidationError = (dateFrom != null) && (!dateFrom.isAfter(LocalDate.now()));

        return isValidationError
                ? Optional.of(new ValidationError(field, message))
                : Optional.empty();
    }
}
