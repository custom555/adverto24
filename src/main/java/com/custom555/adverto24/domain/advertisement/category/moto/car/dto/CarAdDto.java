package com.custom555.adverto24.domain.advertisement.category.moto.car.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarAdDto extends AdDto {
    private String carBrand;
    private String state;
    private Integer mileage;
    private Integer enginePower;
    private Integer prodYear;
}
