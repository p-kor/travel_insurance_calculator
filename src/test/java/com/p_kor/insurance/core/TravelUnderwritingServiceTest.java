package com.p_kor.insurance.core;

import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.testdata.TestDataRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TravelUnderwritingServiceTest {

    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TravelUnderwritingService travelUnderwritingService;

    @Test
    void testThatAgreementPriceEqualsToDaysPeriod() {

        final long DAYS = TestDataRequest.DAYS;
        final TravelCalculatePremiumRequest VALID_REQUEST = TestDataRequest.VALID_REQUEST;
        BigDecimal expectedAgreementPrice = new BigDecimal(DAYS);

        Mockito.when(dateTimeService.daysBetweenDates(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class)))
                .thenReturn(DAYS);

        BigDecimal actualAgreementPrice = travelUnderwritingService.calculateAgreementPrice(VALID_REQUEST);

        Mockito.verify(dateTimeService, Mockito.times(1))
                .daysBetweenDates(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class));

        assertEquals(0, expectedAgreementPrice.compareTo(actualAgreementPrice), "Wrong agreement price");
    }
}