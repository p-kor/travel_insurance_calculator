package com.p_kor.insurance.core.validation;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.ValidationError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {
        TravelCalculatePremiumRequestValidationService.class,
        TravelCalculatePremiumRequestValidationServiceImplTest.class})
@ComponentScan(basePackages = "com.p_kor.insurance.core.validation")
class TravelCalculatePremiumRequestValidationServiceImplTest {

    private TestInfo testInfo;

    @Autowired
    private List<TravelCalculatePremiumRequestValidator> validators;

    @Autowired
    private TravelCalculatePremiumRequestValidationService requestValidationService;


    @BeforeEach
    void setUp(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    @ParameterizedTest(name = "{argumentSetName}")
    @MethodSource("com.p_kor.insurance.testdata.TestDataRequest#validRequestFactory")
    @Tag("IntegrationTest")
    @DisplayName("Test that request gets 0 validation errors")
    void testNoValidationErrors(TravelCalculatePremiumRequest request) {

        List<ValidationError> validationErrors = requestValidationService.validate(request);
        int expected = 0;
        int actual = validationErrors.size();
        String errorMessage = "{%s}: should have no validation errors".formatted(testInfo.getDisplayName());

        assertEquals(expected, actual, errorMessage);
    }

    @ParameterizedTest(name = "{argumentSetName}")
    @MethodSource("com.p_kor.insurance.testdata.TestDataRequest#emptyRequestFactory")
    @Tag("IntegrationTest")
    @DisplayName("Test that request gets 4 validation errors")
    void testMultipleValidationErrors(TravelCalculatePremiumRequest request) {

        List<ValidationError> validationErrors = requestValidationService.validate(request);
        int expected = 4;
        int actual = validationErrors.size();
        String errorMessage = "{%s}: should be 4 validation error".formatted(testInfo.getDisplayName());

        assertEquals(expected, actual, errorMessage);
    }

    @ParameterizedTest(name = "{argumentSetName}")
    @MethodSource("com.p_kor.insurance.testdata.TestDataRequest#invalidRequestFactory")
    @Tag("IntegrationTest")
    @DisplayName("Test that request gets 1 validation errors")
    void testThatHaveValidationError(TravelCalculatePremiumRequest request) {

        List<ValidationError> validationErrors = requestValidationService.validate(request);
        int expected = 1;
        int actual = validationErrors.size();
        String errorMessage = "{%s}: should be 1 validation error".formatted(testInfo.getDisplayName());

        assertEquals(expected, actual, errorMessage);
    }

}