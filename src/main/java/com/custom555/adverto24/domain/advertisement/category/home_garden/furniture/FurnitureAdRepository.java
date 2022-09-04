package com.custom555.adverto24.domain.advertisement.category.home_garden.furniture;

import com.custom555.adverto24.domain.advertisement.AdRepository;
import com.custom555.adverto24.domain.advertisement.enums.FurnitureType;
import com.custom555.adverto24.domain.advertisement.enums.State;

import java.util.Set;

public interface FurnitureAdRepository extends AdRepository<FurnitureAd> {

    Set<FurnitureAd> findAllByFurnitureType(FurnitureType type);
    Set<FurnitureAd> findAllByState(State state);
}
