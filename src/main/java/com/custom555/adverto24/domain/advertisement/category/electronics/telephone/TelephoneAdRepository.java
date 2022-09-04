package com.custom555.adverto24.domain.advertisement.category.electronics.telephone;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.State;

import java.util.Set;

public interface TelephoneAdRepository extends AdRepository<TelephoneAd> {
    Set<TelephoneAd> findAllByState(State state);
}
