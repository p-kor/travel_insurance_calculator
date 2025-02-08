package com.p_kor.insurance.rest;

import com.p_kor.insurance.core.TravelCalculatePremiumService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

    private final TravelCalculatePremiumService calculatePremiumService;

    public TravelCalculatePremiumController(TravelCalculatePremiumService calculatePremiumService) {
        this.calculatePremiumService = calculatePremiumService;
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
        return calculatePremiumService.calculatePremium(request);
    }

}