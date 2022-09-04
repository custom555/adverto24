package com.custom555.adverto24.domain.advertisement.category.real_estate.house;

import com.custom555.adverto24.domain.advertisement.AdRepository;

import java.util.Set;

public interface HouseAdRepository extends AdRepository<HouseAd> {
    Set<HouseAd> findAllByAreaBetween(Integer areaBottom, Integer areaTop);

    Set<HouseAd> findAllByAreaGreaterThanEqual(Integer areaBottom);

    Set<HouseAd> findAllByAreaLessThanEqual(Integer areaTop);

    Set<HouseAd> findAllByFloorCountBetween(Integer floorCountBottom, Integer floorCountTop);

    Set<HouseAd> findAllByFloorCountGreaterThanEqual(Integer floorCountBottom);

    Set<HouseAd> findAllByFloorCountLessThanEqual(Integer floorCountTop);
}
