package com.p_kor.insurance;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class InsuranceApplicationTests {

    private final ApplicationContext applicationContext;

    InsuranceApplicationTests(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    void contextLoads() {
        assertNotNull(applicationContext, "ApplicationContext should not be null");
    }

}
