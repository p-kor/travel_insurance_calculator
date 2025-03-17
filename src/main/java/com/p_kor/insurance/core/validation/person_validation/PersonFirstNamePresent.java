package com.p_kor.insurance.core.validation.person_validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class PersonFirstNamePresent implements TravelCalculatePremiumRequestValidator {

    @Override
    public Optional<ValidationError> validate(TravelCalculatePremiumRequest request) {

        String field = "personFirstName";
        String message = "First name is required";
        String firstName = request.personFirstName();
        boolean isValidationError = (firstName == null) || (firstName.isBlank());

        return isValidationError
                ? Optional.of(new ValidationError(field, message))
                : Optional.empty();
    }
}
