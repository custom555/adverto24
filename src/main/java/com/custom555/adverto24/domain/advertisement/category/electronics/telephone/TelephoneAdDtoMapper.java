package com.custom555.adverto24.domain.advertisement.category.electronics.telephone;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.electronics.telephone.dto.TelephoneAdDto;
import com.custom555.adverto24.domain.advertisement.enums.State;

public class TelephoneAdDtoMapper {
    public static TelephoneAdDto toDto(TelephoneAd telephoneAd){
        TelephoneAdDto dto = (TelephoneAdDto) AdDtoMapper.toDto(telephoneAd,new TelephoneAdDto());
        State state = telephoneAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        return dto;
    }
}
