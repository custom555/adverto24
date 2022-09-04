package com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlatAdDto extends AdDto {
    private Integer area;
    private Integer roomCount;
}
