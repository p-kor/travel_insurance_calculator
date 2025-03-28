package com.p_kor.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.p_kor.insurance.core.TravelCalculatePremiumService;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.log.TravelCalculatePremiumRequestExecutionTimeLogger;
import com.p_kor.insurance.log.TravelCalculatePremiumRequestLogger;
import com.p_kor.insurance.log.TravelCalculatePremiumResponseLogger;
import com.p_kor.insurance.testdata.TestDataRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.json.JsonCompareMode;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TravelCalculatePremiumController.class)
class TravelCalculatePremiumControllerUnitTest {

    final String ENDPOINT = "/insurance/travel/";

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TravelCalculatePremiumService calculatePremiumService;

    @MockitoBean
    private TravelCalculatePremiumRequestLogger requestLogger;

    @MockitoBean
    private  TravelCalculatePremiumResponseLogger responseLogger;

    @MockitoBean
    private TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Tag("UnitTest")
    @DisplayName("Unit test controller for POST \"/insurance/travel/\"")
    void testPostEndPointUnit() throws Exception {

        TravelCalculatePremiumRequest request = TestDataRequest.VALID_REQUEST;
        TravelCalculatePremiumResponse response = TestDataRequest.VALID_RESPONSE;

        Mockito
                .when(calculatePremiumService.calculatePremium(any(TravelCalculatePremiumRequest.class)))
                .thenReturn(response);

        String requestJson = objectMapper.writeValueAsString(request);
        String responseJson = objectMapper.writeValueAsString(response);

        mockMvc
                .perform(post(ENDPOINT)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON_VALUE),
                        content().json(responseJson, JsonCompareMode.STRICT));

        Mockito
                .verify(calculatePremiumService, Mockito.times(1))
                .calculatePremium(any(TravelCalculatePremiumRequest.class));
    }

}