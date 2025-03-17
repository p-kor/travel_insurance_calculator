package com.p_kor.insurance.core.validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
class TravelCalculatePremiumRequestValidationServiceImpl implements TravelCalculatePremiumRequestValidationService {

    private final List<TravelCalculatePremiumRequestValidator> validators;

    @Autowired
    public TravelCalculatePremiumRequestValidationServiceImpl(List<TravelCalculatePremiumRequestValidator> validators) {
        this.validators = validators;
    }

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {

        return validators.stream()
                .map(validator -> validator.validate(request))
                .flatMap(Optional::stream)
                .toList();
    }
}
