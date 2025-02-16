package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.dto.ValidationError;
import com.p_kor.insurance.testdata.TestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    private static DateTimeService dateTimeService;

    @Mock
    private static AgreementPriceService agreementPriceService;

    @Mock
    private static TravelCalculatePremiumRequestValidator requestValidator;

    @Test
    @DisplayName("Test that the response contains correct values")
    void testResponseContainsCorrectValues() {

        BigDecimal ExpectedAgreementPrice = new BigDecimal(TestData.DAYS);
        TravelCalculatePremiumRequest request = TestData.VALID_REQUEST;

        Mockito.when(dateTimeService.daysBetweenDates(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class)))
                .thenReturn(TestData.DAYS);
        Mockito.when(agreementPriceService.calculateAgreementPrice(Mockito.anyLong()))
                .thenReturn(ExpectedAgreementPrice);
        Mockito.when(requestValidator.validate(request))
                .thenReturn(List.of());

        TravelCalculatePremiumService travelCalculatePremiumService =
                new TravelCalculatePremiumServiceImpl(dateTimeService, agreementPriceService, requestValidator);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        Mockito.verify(dateTimeService, Mockito.times(1))
                .daysBetweenDates(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));
        Mockito.verify(agreementPriceService, Mockito.times(1))
                .calculateAgreementPrice(Mockito.anyLong());
        Mockito.verify(requestValidator, Mockito.times(1))
                .validate(Mockito.any(TravelCalculatePremiumRequest.class));

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

        assertAll("Wrong values in response",
                () -> assertEquals(expectedPersonFirstName, actualPersonFirstName, "wrong first name"),
                () -> assertEquals(expectedPersonLastName, actualPersonLastName, "wrong last name"),
                () -> assertEquals(expectedAgreementDateFrom, actualAgreementDateFrom, "wrong agreement start date"),
                () -> assertEquals(expectedAgreementDateTo, actualAgreementDateTo, "wrong agreement end date"),
                () -> assertEquals(ExpectedAgreementPrice, actualAgreementPrice, "wrong agreement price"),
                () -> assertNull(actualValidationErrors, "list of validation errors should be null"));
    }

    @Test
    @DisplayName("Test that the response contains validation errors")
    void testResponseContainsValidationErrors() {

        TravelCalculatePremiumRequest request = TestData.VALID_REQUEST;

        String errorMessage = "validation error";
        ValidationError expectedValidationError = new ValidationError("testField", errorMessage);
        System.out.println(expectedValidationError);

        Mockito.when(requestValidator.validate(Mockito.any(TravelCalculatePremiumRequest.class)))
                .thenReturn(List.of(expectedValidationError));

        TravelCalculatePremiumService travelCalculatePremiumService =
                new TravelCalculatePremiumServiceImpl(dateTimeService, agreementPriceService, requestValidator);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        Mockito.verify(requestValidator, Mockito.times(1))
                .validate(Mockito.any(TravelCalculatePremiumRequest.class));

        List<ValidationError> actualValidationErrors = response.validationErrors();

        assertAll("Wrong values in response",
                () -> assertFalse(actualValidationErrors.isEmpty(), "list of validation errors should be not empty"),
                () -> assertTrue(actualValidationErrors.stream().anyMatch(ve -> ve.message().equals(errorMessage)),
                        "list of validation errors should include error with message " + errorMessage));
    }
}