package com.p_kor.insurance.rest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.FieldSource;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TravelCalculatePremiumControllerIntegrationTest {

    final String BASE_PATH = "rest/";
    final String ENDPOINT = "/insurance/travel/";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private MockMvc mockMvc;

    private static final List<Arguments> argumentSets = List.of(
            Arguments.argumentSet("valid request", "valid_request.json", "valid_response.json", 200),
            Arguments.argumentSet("missed firstname", "missed_firstname_request.json", "missed_firstname_response.json", 200),
            Arguments.argumentSet("empty request", "empty_request.json", "empty_response.json", 200));

    @ParameterizedTest(name = "{argumentSetName}")
    @FieldSource("argumentSets")
    @Tag("IntegrationTest")
    @DisplayName("Integration test for POST /insurance/travel/ endpoint ")
    @SneakyThrows
    void testPostEndPointParam(String requestFileName, String responseFileName, int expectedStatus) {

        String requestLocation = "classpath:" + BASE_PATH + requestFileName;
        Path requestFile = resourceLoader.getResource(requestLocation).getFile().toPath();
        String requestJson = Files.readString(requestFile);

        String responseLocation = "classpath:" + BASE_PATH + responseFileName;
        Path responseFile = resourceLoader.getResource(responseLocation).getFile().toPath();
        String responseJson = Files.readString(responseFile);

        String actualResponse = mockMvc
                .perform(post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().is(expectedStatus))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(responseJson, actualResponse, JSONCompareMode.NON_EXTENSIBLE);
    }

}