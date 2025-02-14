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

        System.out.println(request.getPersonFirstName());
        return (request.getPersonFirstName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest request) {

        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {

        LocalDate dateFrom = request.getAgreementDateFrom();

        if (dateFrom == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be empty"));
        }

        return (dateFrom.isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be earlier than current"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {

        LocalDate dateTo = request.getAgreementDateTo();

        if (dateTo == null) {
            return Optional.of(new ValidationError("agreementDateFrom", "Must not be empty"));
        }

        return (dateTo.isBefore(LocalDate.now()))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be earlier than current"))
                : Optional.empty();
    }

}
