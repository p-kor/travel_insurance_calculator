package com.p_kor.insurance.core.validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidationService {
    public List<ValidationError> validate(TravelCalculatePremiumRequest request);
}
