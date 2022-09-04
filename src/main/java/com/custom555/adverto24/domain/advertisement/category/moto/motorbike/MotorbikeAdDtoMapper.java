package com.custom555.adverto24.domain.advertisement.category.moto.motorbike;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.moto.motorbike.dto.MotorbikeAdDto;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.MotorbikeBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;

public class MotorbikeAdDtoMapper {

    public static MotorbikeAdDto toDto(MotorbikeAd motorbikeAd) {
        MotorbikeAdDto dto = (MotorbikeAdDto) AdDtoMapper.toDto(motorbikeAd, new MotorbikeAdDto());
        State state = motorbikeAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        MotorbikeBrand motorbikeBrand = motorbikeAd.getMotorbikeBrand();
        if(motorbikeBrand != null){
            dto.setMotorbikeBrand(motorbikeBrand.toString());
        }
        dto.setProdYear(motorbikeAd.getProdYear());
        dto.setEnginePower(motorbikeAd.getEnginePower());
        dto.setMileage(motorbikeAd.getMileage());
        return dto;
    }
}
