package com.custom555.adverto24.domain.advertisement.category.real_estate.house;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.real_estate.house.dto.HouseAdDto;

public class HouseAdDtoMapper {
    public static HouseAdDto toDto(HouseAd houseAd) {
        HouseAdDto dto = (HouseAdDto) AdDtoMapper.toDto(houseAd, new HouseAdDto());
        dto.setArea(houseAd.getArea());
        dto.setFloorCount(houseAd.getFloorCount());
        return dto;
    }
}
