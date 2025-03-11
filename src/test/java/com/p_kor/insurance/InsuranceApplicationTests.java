package com.p_kor.insurance;

import com.p_kor.insurance.core.TravelCalculatePremiumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InsuranceApplicationTests {

    private final ApplicationContext applicationContext;

    @Autowired
    InsuranceApplicationTests(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "ApplicationContext should not be null");

        TravelCalculatePremiumService bean = applicationContext.getBean(TravelCalculatePremiumService.class);

        assertNotNull(bean, "The bean TravelCalculatePremiumServiceImpl should not be null");
    }
}
