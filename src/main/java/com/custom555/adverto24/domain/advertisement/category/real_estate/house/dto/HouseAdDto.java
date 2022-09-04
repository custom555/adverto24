package com.custom555.adverto24.domain.advertisement.category.real_estate.house.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseAdDto extends AdDto {
    private Integer area;
    private Integer floorCount;
}
