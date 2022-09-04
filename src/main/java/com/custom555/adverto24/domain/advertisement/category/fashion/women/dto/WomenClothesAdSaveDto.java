package com.custom555.adverto24.domain.advertisement.category.fashion.women.dto;

import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WomenClothesAdSaveDto extends AdSaveDto {
    private String state;
    private String size;
    private String fashionBrand;
}
