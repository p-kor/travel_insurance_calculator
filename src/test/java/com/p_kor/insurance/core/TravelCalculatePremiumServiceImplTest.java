package com.p_kor.insurance.core;

import com.p_kor.insurance.rest.TravelCalculatePremiumRequest;
import com.p_kor.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private static DateTimeService dateTimeService;

    @Mock
    private static AgreementPriceService agreementPriceService;

    private static final long DAYS = 43L;
    private static String firstName;
    private static String lastName;
    private static LocalDate agreementDateFrom;
    private static LocalDate agreementDateTo;
    private static BigDecimal ExpectedAgreementPrice;

    @Test
    @DisplayName("Test that the response contains correct values")
    void testResponseContainsCorrectValues() {
        firstName = "Ivan";
        lastName = "Ivanov";
        agreementDateFrom = LocalDate.of(2020, 1, 1);
        agreementDateTo = agreementDateFrom.plusDays(DAYS);
        ExpectedAgreementPrice = new BigDecimal(DAYS);

        TravelCalculatePremiumRequest request =
                new TravelCalculatePremiumRequest(firstName, lastName, agreementDateFrom, agreementDateTo);

        Mockito.when(dateTimeService.daysBetweenDates(agreementDateFrom, agreementDateTo)).thenReturn(DAYS);
        Mockito.when(agreementPriceService.calculateAgreementPrice(DAYS)).thenReturn(ExpectedAgreementPrice);

        TravelCalculatePremiumService travelCalculatePremiumService =
                new TravelCalculatePremiumServiceImpl(dateTimeService, agreementPriceService);

        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(request);

        Mockito.verify(dateTimeService, Mockito.times(1))
                .daysBetweenDates(agreementDateFrom, agreementDateTo);

        Mockito.verify(agreementPriceService, Mockito.times(1))
                .calculateAgreementPrice(DAYS);

        assertAll("Wrong values in response",
                () -> assertEquals(firstName, response.personFirstName(), "wrong first name"),
                () -> assertEquals(lastName, response.personLastName(), "wrong last name"),
                () -> assertEquals(agreementDateFrom, response.agreementDateFrom(), "wrong agreement start date"),
                () -> assertEquals(agreementDateTo, response.agreementDateTo(), "wrong agreement end date"),
                () -> assertEquals(ExpectedAgreementPrice, response.agreementPrice(), "wrong agreement price"));
    }
}