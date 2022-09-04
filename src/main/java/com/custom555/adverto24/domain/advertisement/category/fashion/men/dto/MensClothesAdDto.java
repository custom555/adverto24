package com.custom555.adverto24.domain.advertisement.category.fashion.men.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensClothesAdDto extends AdDto {
    private String state;
    private String size;
    private String fashionBrand;
}
