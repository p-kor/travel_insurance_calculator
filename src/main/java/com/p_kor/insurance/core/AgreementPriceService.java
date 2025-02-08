package com.p_kor.insurance.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AgreementPriceService {

    public BigDecimal calculateAgreementPrice(long days) {
        return new BigDecimal(days);
    }
}
