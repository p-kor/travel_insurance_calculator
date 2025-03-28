package com.p_kor.insurance.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TravelCalculatePremiumRequestLogger {

    private final ObjectMapper objectMapper;

    public void log(TravelCalculatePremiumRequest request) {

        Marker markerRequestResponse = MarkerFactory.getMarker("REST_LOG");

        String requestJson = "cannot convert to json";
        try {
            requestJson = objectMapper.writeValueAsString(request);

        } catch (JsonProcessingException e) {
            log.error("cannot convert request to json", e);
        }
        log.info(markerRequestResponse, "Received request: {}", requestJson);
    }
}
