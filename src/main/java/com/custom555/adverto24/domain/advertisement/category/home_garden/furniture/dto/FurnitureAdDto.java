package com.custom555.adverto24.domain.advertisement.category.home_garden.furniture.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FurnitureAdDto extends AdDto {
    private String state;
    private String furnitureType;
}
