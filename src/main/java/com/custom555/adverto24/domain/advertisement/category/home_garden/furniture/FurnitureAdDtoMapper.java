package com.custom555.adverto24.domain.advertisement.category.home_garden.furniture;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto.FurnitureAdDto;
import com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto.FurnitureAdSaveDto;
import com.custom555.adverto24.domain.advertisement.enums.FurnitureType;
import com.custom555.adverto24.domain.advertisement.enums.State;

public class FurnitureAdDtoMapper {
    public static FurnitureAdDto toDto(FurnitureAd furnitureAd) {
        FurnitureAdDto dto = (FurnitureAdDto) AdDtoMapper.toDto(furnitureAd, new FurnitureAdDto());
        State state = furnitureAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        FurnitureType furnitureType = furnitureAd.getFurnitureType();
        if(furnitureType != null){
            dto.setFurnitureType(furnitureType.toString());
        }
        return dto;
    }
}
