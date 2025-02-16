package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TravelCalculatePremiumRequestValidatorTest {

    @ParameterizedTest(name = "test with {1}")
    @MethodSource("com.p_kor.insurance.testdata.TestDataRequest#validRequestFactory")
    @DisplayName("Test that valid request brings no errors")
    void testNoValidationErrors(TravelCalculatePremiumRequest request, String testName) {
        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(0, validationErrors.size(), "should be no validation errors");
    }

    @ParameterizedTest(name = "test with {1}")
    @MethodSource("com.p_kor.insurance.testdata.TestDataRequest#emptyRequestFactory")
    @DisplayName("Test that valid request brings no errors")
    void testMultipleValidationErrors(TravelCalculatePremiumRequest request, String testName) {
        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(4, validationErrors.size(), "should be 4 validation error");
    }

    @ParameterizedTest(name = "test with request #{index}: {1}")
    @MethodSource("com.p_kor.insurance.testdata.TestDataRequest#invalidRequestFactory")
    @DisplayName("Test that invalid requests cause errors")
    void testThatHaveValidationError(TravelCalculatePremiumRequest request, String testName) {
        TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();
        List<ValidationError> validationErrors = requestValidator.validate(request);

        assertEquals(1, validationErrors.size(), "should be 1 validation error");
    }

}