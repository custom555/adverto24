package com.custom555.adverto24.domain.advertisement.category.fashion.men;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.fashion.men.dto.MensClothesAdDto;
import com.custom555.adverto24.domain.advertisement.enums.FashionBrand;
import com.custom555.adverto24.domain.advertisement.enums.Size;
import com.custom555.adverto24.domain.advertisement.enums.State;

public class MensClothesAdDtoMapper {
    public static MensClothesAdDto toDto(MensClothesAd mensClothesAd){
        MensClothesAdDto dto = (MensClothesAdDto) AdDtoMapper.toDto(mensClothesAd,new MensClothesAdDto());
        State state = mensClothesAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        Size size = mensClothesAd.getSize();
        if(size != null){
            dto.setSize(size.toString());
        }
        FashionBrand fashionBrand = mensClothesAd.getFashionBrand();
        if(fashionBrand != null){
            dto.setFashionBrand(fashionBrand.toString());
        }
        return dto;
    }
}
