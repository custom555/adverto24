package com.custom555.adverto24.domain.advertisement.category.fashion.women;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.fashion.women.dto.WomenClothesAdDto;
import com.custom555.adverto24.domain.advertisement.enums.FashionBrand;
import com.custom555.adverto24.domain.advertisement.enums.Size;
import com.custom555.adverto24.domain.advertisement.enums.State;

public class WomenClothesAdDtoMapper {
    public static WomenClothesAdDto toDto(WomenClothesAd womenClothesAd) {
        WomenClothesAdDto dto = (WomenClothesAdDto) AdDtoMapper.toDto(womenClothesAd, new WomenClothesAdDto());
        State state = womenClothesAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        Size size = womenClothesAd.getSize();
        if(size != null){
            dto.setSize(size.toString());
        }
        FashionBrand fashionBrand = womenClothesAd.getFashionBrand();
        if(fashionBrand != null){
            dto.setFashionBrand(fashionBrand.toString());
        }
        return dto;
    }
}
