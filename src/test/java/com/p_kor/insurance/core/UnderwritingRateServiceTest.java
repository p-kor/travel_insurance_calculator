package com.p_kor.insurance.core;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnderwritingRateServiceTest {

    @Test
    void testThatAgreementPriceEqualsToDaysPeriod() {
        UnderwritingRateService agreementPriceService = new UnderwritingRateService();
        long days = 43L;
        BigDecimal expectedAgreementPrice = new BigDecimal(days);
        BigDecimal actualAgreementPrice = agreementPriceService.calculateAgreementPrice(days);

        assertEquals(expectedAgreementPrice, actualAgreementPrice, "Wrong agreement price");
    }
}