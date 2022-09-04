package com.custom555.adverto24.domain.advertisement.category.electronics.audio;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.State;

import java.util.Set;

public interface AudioAdRepository extends AdRepository<AudioAd> {
    Set<AudioAd> findAllByState(State state);
}
