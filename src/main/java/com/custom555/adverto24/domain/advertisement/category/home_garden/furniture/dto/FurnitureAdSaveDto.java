package com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto;

import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FurnitureAdSaveDto extends AdSaveDto {
    private String state;
    private String furnitureType;
}
