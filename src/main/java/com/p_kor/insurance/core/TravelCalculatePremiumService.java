package com.p_kor.insurance.core;


import com.p_kor.insurance.rest.TravelCalculatePremiumRequest;
import com.p_kor.insurance.rest.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
