package com.custom555.adverto24.domain.advertisement.category.moto.motorbike.dto;

import com.custom555.adverto24.domain.advertisement.AdDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotorbikeAdDto extends AdDto {
    private String motorbikeBrand;
    private String state;
    private Integer mileage;
    private Integer enginePower;
    private Integer prodYear;
}
