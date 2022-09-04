package com.custom555.adverto24.domain.advertisement.category.real_estate.flat.dto;

import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlatAdSaveDto extends AdSaveDto {
    private Integer area;
    private Integer roomCount;
}
