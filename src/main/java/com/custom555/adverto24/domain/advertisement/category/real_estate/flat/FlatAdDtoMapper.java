package com.custom555.adverto24.domain.advertisement.category.real_estate.flat;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto.FlatAdDto;

public class FlatAdDtoMapper {
    public static FlatAdDto toDto(FlatAd flatAd) {
        FlatAdDto dto = (FlatAdDto) AdDtoMapper.toDto(flatAd, new FlatAdDto());
        dto.setArea(flatAd.getArea());
        dto.setRoomCount(dto.getRoomCount());
        return dto;
    }
}
