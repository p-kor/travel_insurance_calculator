package com.p_kor.insurance.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TravelCalculatePremiumResponseLogger {

    private final ObjectMapper objectMapper;

    public void log(TravelCalculatePremiumResponse response) {

        Marker markerRequestResponse = MarkerFactory.getMarker("REST_LOG");

        String requestJson = "cannot convert to json";
        try {
            requestJson = objectMapper.writeValueAsString(response);

        } catch (JsonProcessingException e) {
            log.error("cannot convert response to json", e);
        }
        log.info(markerRequestResponse,"Response: {}", requestJson);
    }
}
