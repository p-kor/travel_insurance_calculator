package com.p_kor.insurance.core.validation.date_validation;

import com.p_kor.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.testdata.TestDataRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateToAfterDateFromTest {

    private TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AgreementDateToAfterDateFrom();
    }

    @Test
    @Tag("UnitTest")
    @DisplayName("Valid request produces no errors")
    void testValidRequestHasNoValidationErrors() {

        TravelCalculatePremiumRequest request = TestDataRequest.VALID_REQUEST;
        Optional<ValidationError> result = validator.validate(request);

        assertTrue(result.isEmpty(), "Should have no validation errors");
    }

    @Test
    @Tag("UnitTest")
    @DisplayName("Empty request produces a error")
    void testValidRequestHasSingleValidationErrors() {

        TravelCalculatePremiumRequest request = TravelCalculatePremiumRequest.builder()
                .agreementDateFrom(LocalDate.now().plusDays(2))
                .agreementDateTo(LocalDate.now().plusDays(1))
                .build();

        Optional<ValidationError> result = validator.validate(request);

        assertTrue(result.isPresent(), "Should have validation error");

        ValidationError validationError = result.get();

        String expectedField = "agreementDateTo";
        String actualField = validationError.field();
        String wrongFieldAssertMessage = "validation error should contain field \"" + expectedField + "\"";

        String expectedErrorMessage = "Date TO must be after Date FROM";
        String actualErrorMessage = validationError.message();
        String wrongErrorMessageAssertMessage = "validation error should contain message \"" + expectedErrorMessage + "\"";

        assertAll(
                () -> assertEquals(expectedField, actualField, wrongFieldAssertMessage),
                () -> assertEquals(expectedErrorMessage, actualErrorMessage, wrongErrorMessageAssertMessage)
        );
    }

}