package com.p_kor.insurance.core.validation.person_validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonLastNamePresent implements TravelCalculatePremiumRequestValidator {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {

        String field = "personLastName";
        String message = "Last name is required";
        String lastName = request.personLastName();
        boolean isValidationError = (lastName == null) || (lastName.isBlank());

        return isValidationError
                ? Optional.of(new ValidationError(field, message))
                : Optional.empty();
    }
}
