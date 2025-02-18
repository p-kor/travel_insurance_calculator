package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(request).ifPresent(errors::add);
        validatePersonLastName(request).ifPresent(errors::add);
        validateAgreementDateFrom(request).ifPresent(errors::add);
        validateAgreementDateTo(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest request) {

        String personFirstName = request.personFirstName();

        return (personFirstName == null || personFirstName.isBlank())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {

        String personLastName = request.personLastName();

        return (personLastName == null || personLastName.isBlank())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {

        LocalDate dateFrom = request.agreementDateFrom();

        if (dateFrom == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be empty"));
        }

        return (dateFrom.isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be earlier than current"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {

        LocalDate dateFrom = request.agreementDateFrom();
        LocalDate dateTo = request.agreementDateTo();

        if (dateTo == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be empty"));
        }

        if (dateFrom == null) {
            return Optional.empty();        // error already found
        }

        return (!dateTo.isAfter(dateFrom))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be after agreementDateFrom"))
                : Optional.empty();
    }

}
