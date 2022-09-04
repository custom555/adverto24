package com.custom555.adverto24.domain.advertisement.category.moto.car.dto;

import com.custom555.adverto24.domain.advertisement.AdSaveDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarAdSaveDto extends AdSaveDto {
    private String carBrand;
    private String state;
    private Integer mileage;
    private Integer enginePower;
    private Integer prodYear;
}
