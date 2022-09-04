package com.custom555.adverto24.domain.advertisement.category.moto.car;

import com.custom555.adverto24.domain.advertisement.AdDtoMapper;
import com.custom555.adverto24.domain.advertisement.category.moto.car.dto.CarAdDto;
import com.custom555.adverto24.domain.advertisement.enums.CarBrand;
import com.custom555.adverto24.domain.advertisement.enums.State;

public class CarAdDtoMapper {

    public static CarAdDto toDto(CarAd carAd) {
        CarAdDto dto = (CarAdDto) AdDtoMapper.toDto(carAd, new CarAdDto());
        State state = carAd.getState();
        if(state != null){
            dto.setState(state.toString());
        }
        CarBrand carBrand = carAd.getCarBrand();
        if(carBrand != null){
            dto.setCarBrand(carBrand.toString());
        }
        dto.setProdYear(carAd.getProdYear());
        dto.setEnginePower(carAd.getEnginePower());
        dto.setMileage(carAd.getMileage());
        return dto;
    }
}
