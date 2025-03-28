package com.p_kor.insurance.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Stopwatch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
@RequiredArgsConstructor
public class TravelCalculatePremiumRequestExecutionTimeLogger {

    private final ObjectMapper objectMapper;

    public void log(Stopwatch stopwatch) {

        Marker markerRequestResponse = MarkerFactory.getMarker("REST_LOG");

        double elapsedTime = (double) stopwatch.elapsed(TimeUnit.MICROSECONDS) / 1000;
        log.info(markerRequestResponse, "Request processing time: {} ms", elapsedTime);
    }
}
