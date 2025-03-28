package com.p_kor.insurance.rest;

import com.google.common.base.Stopwatch;
import com.p_kor.insurance.core.TravelCalculatePremiumService;
import com.p_kor.insurance.dto.TravelCalculatePremiumRequest;
import com.p_kor.insurance.dto.TravelCalculatePremiumResponse;
import com.p_kor.insurance.log.TravelCalculatePremiumRequestLogger;
import com.p_kor.insurance.log.TravelCalculatePremiumResponseLogger;
import com.p_kor.insurance.log.TravelCalculatePremiumRequestExecutionTimeLogger;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Slf4j
class TravelCalculatePremiumController {

    private final TravelCalculatePremiumService calculatePremiumService;
    private final TravelCalculatePremiumRequestLogger requestLogger;
    private final TravelCalculatePremiumResponseLogger responseLogger;
    private final TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;

    @PostMapping(
            path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {

        requestLogger.log(request);

        Stopwatch stopwatch = Stopwatch.createStarted();
        TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
        executionTimeLogger.log(stopwatch.stop());

        responseLogger.log(response);

        return response;
    }

}