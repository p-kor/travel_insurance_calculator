package com.p_kor.insurance.core.validation.date_validation;

import com.p_kor.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.testdata.TestDataRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AgreementDateFromPresentTest {

    private TravelCalculatePremiumRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new AgreementDateFromPresent();
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

        TravelCalculatePremiumRequest request = TestDataRequest.EMPTY_REQUEST;
        Optional<ValidationError> result = validator.validate(request);

        assertTrue(result.isPresent(), "Should have validation error");

        ValidationError validationError = result.get();

        String expectedField = "agreementDateFrom";
        String actualField = validationError.field();
        String wrongFieldAssertMessage = "validation error should contain field \"" + expectedField + "\"";

        String expectedErrorMessage = "Date FROM is required";
        String actualErrorMessage = validationError.message();
        String wrongErrorMessageAssertMessage = "validation error should contain message \"" + expectedErrorMessage + "\"";

        assertAll(
                () -> assertEquals(expectedField, actualField, wrongFieldAssertMessage),
                () -> assertEquals(expectedErrorMessage, actualErrorMessage, wrongErrorMessageAssertMessage)
        );
    }

}