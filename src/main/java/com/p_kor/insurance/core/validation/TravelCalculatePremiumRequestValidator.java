package com.p_kor.insurance.core.validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;

import java.util.Optional;

public interface TravelCalculatePremiumRequestValidator {
    Optional<ValidationError> validate(TravelCalculatePremiumRequest request);
}
