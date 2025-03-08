package com.p_kor.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.p_kor.insurance.core.TravelCalculatePremiumService;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.testdata.TestDataRequest;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TravelCalculatePremiumController.class)
class TravelCalculatePremiumControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TravelCalculatePremiumService calculatePremiumService;

    @Test
    void testPostEndPoint() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());

        TravelCalculatePremiumRequest request = TestDataRequest.VALID_REQUEST;
        TravelCalculatePremiumResponse response = TestDataRequest.VALID_RESPONSE;
        when(calculatePremiumService.calculatePremium(any(TravelCalculatePremiumRequest.class))).thenReturn(response);

        String requestJson = objectMapper.writeValueAsString(request);

        mockMvc
                .perform(post("/insurance/travel/")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_VALUE),
                        jsonPath("$.personFirstName", is(response.personFirstName())),
                        jsonPath("$.personLastName", is(response.personLastName())),
                        jsonPath("$.agreementDateFrom", is(response.agreementDateFrom().toString())),
                        jsonPath("$.agreementDateTo", is(response.agreementDateTo().toString())),
                        jsonPath("$.validationErrors", is(IsNull.nullValue())),
                        jsonPath("$.agreementPrice", is(response.agreementPrice()), BigDecimal.class));
    }

}