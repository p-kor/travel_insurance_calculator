package com.p_kor.insurance.core.validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidationServiceImpl implements TravelCalculatePremiumRequestValidationService {

    private final List<TravelCalculatePremiumRequestValidator> validators;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {

        return validators.stream()
                .map(validator -> validator.validate(request))
                .flatMap(Optional::stream)
                .toList();
    }
}
