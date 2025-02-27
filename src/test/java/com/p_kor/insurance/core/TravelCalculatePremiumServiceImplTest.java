package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.testdata.TestDataRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelUnderwritingService travelUnderwritingService;

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl premiumService;

    @Test
    @DisplayName("Test that response contains correct values and no errors")
    void testResponseContainsCorrectValuesAndNoErrors() {

        BigDecimal expectedAgreementPrice = new BigDecimal(TestDataRequest.DAYS);
        TravelCalculatePremiumRequest request = TestDataRequest.VALID_REQUEST;

        Mockito.when(requestValidator.validate(Mockito.any(TravelCalculatePremiumRequest.class)))
                .thenReturn(List.of());
        Mockito.when(travelUnderwritingService.calculateAgreementPrice(Mockito.any(TravelCalculatePremiumRequest.class)))
                .thenReturn(expectedAgreementPrice);


        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        Mockito.verify(requestValidator, Mockito.times(1))
                .validate(Mockito.any(TravelCalculatePremiumRequest.class));
        Mockito.verify(travelUnderwritingService, Mockito.times(1))
                .calculateAgreementPrice(Mockito.any(TravelCalculatePremiumRequest.class));


        String expectedPersonFirstName = request.personFirstName();
        String expectedPersonLastName = request.personLastName();
        LocalDate expectedAgreementDateFrom = request.agreementDateFrom();
        LocalDate expectedAgreementDateTo = request.agreementDateTo();

        String actualPersonFirstName = response.personFirstName();
        String actualPersonLastName = response.personLastName();
        LocalDate actualAgreementDateFrom = response.agreementDateFrom();
        LocalDate actualAgreementDateTo = response.agreementDateTo();
        BigDecimal actualAgreementPrice = response.agreementPrice();
        List<ValidationError> actualValidationErrors = response.validationErrors();

        assertAll("Check values in response",
                () -> assertEquals(expectedPersonFirstName, actualPersonFirstName, "wrong first name"),
                () -> assertEquals(expectedPersonLastName, actualPersonLastName, "wrong last name"),
                () -> assertEquals(expectedAgreementDateFrom, actualAgreementDateFrom, "wrong agreement start date"),
                () -> assertEquals(expectedAgreementDateTo, actualAgreementDateTo, "wrong agreement end date"),
                () -> assertEquals(expectedAgreementPrice, actualAgreementPrice, "wrong agreement price"),
                () -> assertNull(actualValidationErrors, "list of validation errors should be null"));
    }

    @Test
    @DisplayName("Test that response contains validation error")
    void testResponseContainsValidationError() {

        TravelCalculatePremiumRequest request = TestDataRequest.VALID_REQUEST;
        ValidationError expectedValidationError = new ValidationError("testField", "validation error");

        Mockito.when(requestValidator.validate(Mockito.any(TravelCalculatePremiumRequest.class)))
                .thenReturn(List.of(expectedValidationError));

        TravelCalculatePremiumResponse response = premiumService.calculatePremium(request);

        Mockito.verify(requestValidator, Mockito.times(1))
                .validate(Mockito.any(TravelCalculatePremiumRequest.class));
        Mockito.verifyNoInteractions(travelUnderwritingService);

        String actualPersonFirstName = response.personFirstName();
        String actualPersonLastName = response.personLastName();
        LocalDate actualAgreementDateFrom = response.agreementDateFrom();
        LocalDate actualAgreementDateTo = response.agreementDateTo();
        BigDecimal actualAgreementPrice = response.agreementPrice();
        List<ValidationError> actualValidationErrors = response.validationErrors();

        assertAll("Check response with validation error",
                () -> assertNull(actualPersonFirstName, "person first name should be null"),
                () -> assertNull(actualPersonLastName, "person last name should be null"),
                () -> assertNull(actualAgreementDateFrom, "agreement start date should be null"),
                () -> assertNull(actualAgreementDateTo, "agreement end date should be null"),
                () -> assertNull(actualAgreementPrice, "agreement agreement price should be null"),
                () -> assertEquals(1, actualValidationErrors.size(),
                        "list of validation errors should contain one validation error"),
                () -> assertEquals(expectedValidationError, actualValidationErrors.getFirst(),
                        "list of validation errors should contain the expected validation error"));
    }
}