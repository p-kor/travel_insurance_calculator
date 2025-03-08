package com.p_kor.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.testdata.TestDataRequest;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPostEndPoint() throws Exception {
        TravelCalculatePremiumRequest request = TestDataRequest.VALID_REQUEST;
        TravelCalculatePremiumResponse response = TestDataRequest.VALID_RESPONSE;
        String requestJson;

        requestJson = objectMapper.writeValueAsString(request);

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