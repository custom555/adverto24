package com.custom555.adverto24.domain.advertisement.category.real_estate.flat;

import com.custom555.adverto24.domain.advertisement.AdRepository;

import java.util.Set;

public interface FlatAdRepository extends AdRepository<FlatAd> {

    Set<FlatAd> findAllByAreaBetween(Integer areaBottom, Integer areaTop);
    Set<FlatAd> findAllByAreaGreaterThanEqual(Integer areaBottom);
    Set<FlatAd> findAllByAreaLessThanEqual(Integer areaTop);
    Set<FlatAd> findAllByRoomCountBetween(Integer roomCountBottom, Integer roomCountTop);
    Set<FlatAd> findAllByRoomCountGreaterThanEqual(Integer roomCountBottom);
    Set<FlatAd> findAllByRoomCountLessThanEqual(Integer roomCountTop);
}
